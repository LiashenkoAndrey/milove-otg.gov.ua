package gov.milove.forum.repositories.jpa;

import gov.milove.forum.model.dto.TopicDto;
import gov.milove.forum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {


    @Query("select t from Topic t")
    List<TopicDto> getList();
}
