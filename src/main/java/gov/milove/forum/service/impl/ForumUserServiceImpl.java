package gov.milove.forum.service.impl;

import gov.milove.main.domain.Image;
import gov.milove.forum.dto.NewForumUserDto;
import gov.milove.forum.domain.ForumUser;
import gov.milove.main.repository.jpa.AppUserRepo;
import gov.milove.forum.repository.jpa.ForumUserRepo;
import gov.milove.main.repository.mongo.ImageRepo;
import gov.milove.forum.service.ForumUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Log4j2
public class ForumUserServiceImpl implements ForumUserService {

    private final AppUserRepo appUserRepo;

    private final ImageRepo imageRepo;

    private final ForumUserRepo forumUserRepo;

    @Override
    public ForumUser saveNewUser(NewForumUserDto dto, String appUserId) {
        ForumUser newForumUser = ForumUser.builder()
                .id(appUserId)
                .appUser(appUserRepo.getReferenceById(appUserId))
                .nickname(dto.getNickname())
                .aboutMe(dto.getAboutMe())
                .lastWasOnline(new Date())
                .build();

        if (dto.getAvatarImageFile() != null) {
            Image savedAvatar = imageRepo.save(new Image(dto.getAvatarImageFile()));
            newForumUser.setAvatar(savedAvatar.getId());
        } else {
            newForumUser.setAvatar(dto.getGoogleAvatar());
        }
        ForumUser saved = forumUserRepo.save(newForumUser);

        log.info("New forum user was saved! {}", newForumUser);
        return saved;
    }
}
