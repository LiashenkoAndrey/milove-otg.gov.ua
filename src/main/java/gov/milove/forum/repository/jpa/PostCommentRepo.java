package gov.milove.forum.repository.jpa;

import gov.milove.forum.dto.PostCommentDto;
import gov.milove.forum.domain.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostCommentRepo extends JpaRepository<PostComment, Long> {

    @Query("select c from PostComment c where c.id = :id")
    PostCommentDto getPostDtoById(@Param("id") Long id);
}
