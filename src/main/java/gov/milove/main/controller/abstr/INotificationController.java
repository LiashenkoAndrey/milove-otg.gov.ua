package gov.milove.controllers.abstr;

import gov.milove.domain.adminNotification.Notification;
import gov.milove.domain.adminNotification.NotificationDtoWithViews;
import gov.milove.domain.dto.NewNotificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Notification controller")
@RequestMapping("/api")
public interface INotificationController {

    @Operation(summary = "Get the number of actual notifications")
    @GetMapping("/protected/admin/notification/totalNumber")
    Long getTotalNumberOfActualNotifications(@RequestParam String encodedUserId);

    @Operation(summary = "Get all notifications")
    @GetMapping("/protected/admin/notification/all")
    List<NotificationDtoWithViews> getAll(@RequestParam String encodedUserId);

    @Operation(summary = "Get notification by id")
    @GetMapping("/protected/admin/notification/{id}")
    Notification getById(@PathVariable Long id, @RequestParam Boolean isViewed, @RequestParam String encodedUserId);

    @Operation(summary = "Create new notification")
    @PostMapping("/protected/admin/notification/new")
    Notification createNew(@RequestBody NewNotificationDto n);

    @Operation(summary = "Edit notification")
    @PutMapping("/protected/admin/notification/{id}/edit")
    Notification createNew(@RequestBody NewNotificationDto n, @PathVariable Long id);

    @Operation(summary = "Delete notification")
    @Transactional
    @DeleteMapping("/protected/admin/notification/{id}/delete")
    Long createNew(@PathVariable Long id);
}
