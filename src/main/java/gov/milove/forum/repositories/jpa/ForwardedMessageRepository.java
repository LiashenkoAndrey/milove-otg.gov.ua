package gov.milove.forum.repositories.jpa;

import gov.milove.forum.model.message.ForwardedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForwardedMessageRepository extends JpaRepository<ForwardedMessage, Long> {
}
