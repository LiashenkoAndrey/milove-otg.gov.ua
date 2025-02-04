package gov.milove.forum.repository.jpa;

import gov.milove.forum.domain.ForumUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ForumUserRepo extends JpaRepository<ForumUser, String> {

    @Query("select count(*) from ForumUser")
    Integer getActiveUsersAmount();

    @Transactional
    @Modifying
    @Query("update ForumUser u set u.isOnline = :isOnline where u.id = :userId")
    int updateUserOnlineStatusById(@Param("userId") String userId, @Param("isOnline") Boolean isOnline);
}
