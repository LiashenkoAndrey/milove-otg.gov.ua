package gov.milove.forum.repository.jpa;

import gov.milove.forum.domain.ForwardedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForwardedMessageRepo extends JpaRepository<ForwardedMessage, Long> {
}
