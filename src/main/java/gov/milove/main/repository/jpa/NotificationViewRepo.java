package gov.milove.main.repository.jpa;

import gov.milove.main.domain.NotificationView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationViewRepo extends JpaRepository<NotificationView, Long> {

    void deleteAllByNotificationId(Long id);
}
