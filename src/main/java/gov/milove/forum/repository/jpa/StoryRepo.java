package gov.milove.forum.repository.jpa;

import gov.milove.forum.dto.UserStoryDto;
import gov.milove.forum.domain.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StoryRepo extends JpaRepository<Story, Long> {

    @Query("select s.id as id, s.author as author, s.createdOn as createdOn, s.imageId as imageId, s.text as text from Story s where s.id = :id")
    Optional<UserStoryDto> findUserStoryById(@Param("id") Long id);
}
