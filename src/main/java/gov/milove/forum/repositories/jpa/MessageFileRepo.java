package gov.milove.forum.repositories.jpa;

import gov.milove.forum.model.message.MessageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageFileRepo extends JpaRepository<MessageFile, Long> {

    @Query(value = "select count(*) from forum.message_file mf where mf.file_id = :messageFileId", nativeQuery = true)
    Integer amountOfUsed(@Param("messageFileId") Long messageFileId);
}
