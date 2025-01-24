package gov.milove.forum.repository.jpa;

import gov.milove.forum.domain.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatRepo extends JpaRepository<UserChat, Long> {

}
