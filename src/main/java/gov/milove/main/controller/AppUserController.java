package gov.milove.main.controller;

import gov.milove.main.domain.AdminMetadata;
import gov.milove.main.domain.AppUser;
import gov.milove.main.domain.User;
import gov.milove.main.dto.AdminMetadataDto;
import gov.milove.main.dto.UserDto;
import gov.milove.forum.dto.AppUserDto;
import gov.milove.main.repository.jpa.AppUserRepo;
import gov.milove.main.repository.mongo.AdminMetadataRepo;
import gov.milove.main.util.Util;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserRepo appUserRepo;
    private final AdminMetadataRepo adminMetadataRepo;

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = em.createQuery("SELECT a FROM User a", User.class).getResultList();
        log.info("users = {}", users);
        return users;
    }

    @PostMapping("/protected/user/new")
    public String newAppUser(@Valid @RequestBody AppUserDto dto) {
        log.info("new user: " + dto);

        AppUser appUser = AppUser.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .avatarContentType(dto.getAvatarContentType())
                .avatarUrl(dto.getAvatarUrl())
                .avatarBase64Image(dto.getBase64Avatar())
                .build();

        log.info("user - {}" , appUser);
        AppUser saved = appUserRepo.save(appUser);
        return saved.getId();
    }

    @GetMapping("/protected/user/isRegistered/id/{encodedUserId}")
    public UserDto isRegistered(@PathVariable String encodedUserId, @RequestParam Boolean isAdmin) {
        String userId = Util.decodeUriComponent(encodedUserId);

        AdminMetadata adminMetadata = null;

        if (isAdmin) {
            log.info("is Admin, get meta...");
            adminMetadata = adminMetadataRepo.findById(userId)
                    .orElse(adminMetadataRepo.save(AdminMetadata.builder()
                            .isDocumentsPageTourCompleted(false)
                            .isShowConfirmWhenDeleteDocument(true)
                            .isShowModalTourWhenUserOnDocumentsPage(true)
                            .userId(userId)
                            .build()));
        }

        log.info("admin meta = {}", adminMetadata);
        Boolean isExist = appUserRepo.existsById(userId);
        log.info("user id - {}, isExist - {}", userId, isExist);
        AppUser appUser =  appUserRepo.findById(encodedUserId).orElse(null);
        log.info(appUser);
        return new UserDto(appUser != null ,adminMetadata, appUser);
    }

    @GetMapping("/protected/user/id/{id}")
    public AppUser getUserById(@PathVariable String id) {
        return appUserRepo.findById(id).orElseThrow(EntityExistsException::new);
    }

    @GetMapping("/protected/appUser/{id}")
    public UserDto getUserMetaById(@PathVariable String id) {
        log.info("user id {}", id);
        AdminMetadata adminMetadata = adminMetadataRepo.findById(id).orElse(null);
        AppUser appUser = appUserRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return new UserDto(adminMetadata, appUser);
    }


    @PutMapping("/protected/user/adminMeta/update")
    public AdminMetadata updateAdminMeta(@RequestBody AdminMetadata adminMetadata) {
        return  adminMetadataRepo.save(adminMetadata);
    }

    @PostMapping("/protected/adminMeta")
    public AdminMetadata saveMetaData(@RequestBody AdminMetadata metadata) {
        return adminMetadataRepo.save(metadata);
    }

    @PutMapping("/protected/adminMeta/{id}/update")
    public void setIsDocumentsPageTourCompleted(@RequestBody AdminMetadataDto dto, @PathVariable String id) {
        AdminMetadata metadata = adminMetadataRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        if (dto.getIsDocumentsPageTourCompleted() != null) metadata.setIsDocumentsPageTourCompleted(dto.getIsDocumentsPageTourCompleted());
        if (dto.getIsShowConfirmWhenDeleteDocument() != null) metadata.setIsShowConfirmWhenDeleteDocument(dto.getIsShowConfirmWhenDeleteDocument());
        adminMetadataRepo.save(metadata);
    }
}
