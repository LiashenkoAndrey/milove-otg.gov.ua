package gov.milove.main.controller.impl;

import gov.milove.main.controller.NotificationController;
import gov.milove.main.domain.Notification;
import gov.milove.main.domain.NotificationView;
import gov.milove.main.dto.NewNotificationDto;
import gov.milove.main.dto.NotificationDtoWithViews;
import gov.milove.main.repository.jpa.AppUserRepo;
import gov.milove.main.repository.jpa.NotificationRepo;
import gov.milove.main.repository.jpa.NotificationViewRepo;
import gov.milove.main.util.Util;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Log4j2
public class NotificationControllerImpl implements NotificationController {

    private final NotificationRepo repo;
    private final AppUserRepo appUserRepo;
    private final NotificationViewRepo viewRepo;

    @Override
    public Long getTotalNumberOfActualNotifications(String encodedUserId) {
        String userId = Util.decodeUriComponent(encodedUserId);
        log.info("Get total number of notifications");
        log.info("user id {}", userId);
        return repo.getTotalNumberOfActualNotifications(userId);
    }

    @Override
    public List<NotificationDtoWithViews> getAll(String encodedUserId) {
        String userId = Util.decodeUriComponent(encodedUserId);
        log.info("Get All notification");
        return repo.getWithViews(userId);
    }

    @Override
    public Notification getById(Long id, Boolean isViewed, String encodedUserId) {
        if (!isViewed) {
            String userId = Util.decodeUriComponent(encodedUserId);
            log.info("notif not viewed! user = {} save view... ", userId);
            viewRepo.save(new NotificationView(id, userId));
        }
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Notification createNew(NewNotificationDto n) {
        log.info("new notification - {}", n);
        Notification saved = repo.save(new Notification(n.getMessage(), n.getText(), appUserRepo.getReferenceById(n.getAuthorId())));

        log.info("saved a new notification! {}", saved);
        return repo.findById(saved.getId()).orElseThrow(EntityNotFoundException::new);
    }


    //Rename if it doesn't affect the front-end part
    @Override
    public Notification createNew(NewNotificationDto n, Long id) {
        log.info("edit notification! {} id = {}", n, id);
        Notification notification = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        notification.setText(n.getText());
        notification.setMessage(n.getMessage());
        return repo.save(notification);
    }

    //Rename if it doesn't affect the front-end part
    @Override
    @Transactional
    public Long createNew(Long id) {
        log.info("Delete a notification {}", id);
        viewRepo.deleteAllByNotificationId(id);
        repo.deleteById(id);
        return id;
    }
}
