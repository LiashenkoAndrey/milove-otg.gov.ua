package gov.milove.forum.repositories.jpa;

import gov.milove.forum.model.dto.PostCommentDto;
import gov.milove.forum.model.post.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostCommentRepo extends JpaRepository<PostComment, Long> {

    @Query("select c from PostComment c where c.id = :id")
    PostCommentDto getPostDtoById(@Param("id") Long id);
}
