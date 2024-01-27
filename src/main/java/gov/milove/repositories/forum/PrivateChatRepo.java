package gov.milove.repositories.forum;

import gov.milove.domain.forum.PrivateChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PrivateChatRepo  extends JpaRepository<PrivateChat, Long> {

    @Query(value = "select * from forum.private_chat p where p.user1_id = :user1_id and p.user2_id = :user2_id", nativeQuery = true)
    Optional<PrivateChat> findByUser1AndUser2(@Param("user1_id") String user1_id, @Param("user2_id") String user2_id);
}
