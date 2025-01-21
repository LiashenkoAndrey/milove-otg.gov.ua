package gov.milove.forum.repositories.jpa;

import gov.milove.forum.model.chat.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatRepo extends JpaRepository<UserChat, Long> {

}
