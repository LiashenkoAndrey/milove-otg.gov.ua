package gov.milove.main.controller;

import gov.milove.main.domain.Notification;
import gov.milove.main.dto.NotificationDtoWithViews;
import gov.milove.main.domain.NotificationView;
import gov.milove.main.dto.NewNotificationDto;
import gov.milove.main.repository.jpa.AppUserRepo;
import gov.milove.main.repository.jpa.NotificationRepo;
import gov.milove.main.repository.jpa.NotificationViewRepo;
import gov.milove.main.util.Util;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class NotificationController {

    private final NotificationRepo repo;
    private final AppUserRepo appUserRepo;
    private final NotificationViewRepo viewRepo;

    @GetMapping("/protected/admin/notification/totalNumber")
    public Long getTotalNumberOfActualNotifications(@RequestParam String encodedUserId) {
        String userId = Util.decodeUriComponent(encodedUserId);
        log.info("Get total number of notifications");
        log.info("user id {}", userId);
        return repo.getTotalNumberOfActualNotifications(userId);
    }

    @GetMapping("/protected/admin/notification/all")
    public List<NotificationDtoWithViews> getAll(@RequestParam String encodedUserId) {
        String userId = Util.decodeUriComponent(encodedUserId);
        log.info("Get All notification");
        return repo.getWithViews(userId);
    }

    @GetMapping("/protected/admin/notification/{id}")
    public Notification getById(@PathVariable Long id, @RequestParam Boolean isViewed, @RequestParam String encodedUserId) {
        if (!isViewed) {
            String userId = Util.decodeUriComponent(encodedUserId);
            log.info("notif not viewed! user = {} save view... ", userId);
            viewRepo.save(new NotificationView(id, userId));
        } 
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping("/protected/admin/notification/new")
    public Notification createNew(@RequestBody NewNotificationDto n) {
        log.info("new notification - {}", n);
        Notification saved = repo.save(
                new Notification(
                        n.getMessage(),
                        n.getText(),
                        appUserRepo.getReferenceById(n.getAuthorId())
                )
        );

        log.info("saved a new notification! {}", saved);
        return repo.findById(saved.getId()).orElseThrow(EntityNotFoundException::new);
    }


    @PutMapping("/protected/admin/notification/{id}/edit")
    public Notification createNew(@RequestBody NewNotificationDto n, @PathVariable Long id) {
        log.info("edit notification! {} id = {}", n, id);
        Notification notification = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        notification.setText(n.getText());
        notification.setMessage(n.getMessage());
        return repo.save(notification);
    }

    @Transactional
    @DeleteMapping("/protected/admin/notification/{id}/delete")
    public Long createNew(@PathVariable Long id) {
        log.info("Delete a notification {}", id);
        viewRepo.deleteAllByNotificationId(id);
        repo.deleteById(id);
        return id;
    }
}
