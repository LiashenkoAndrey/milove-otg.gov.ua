package gov.milove.forum.repository.jpa;

import gov.milove.forum.dto.TopicDto;
import gov.milove.forum.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepo extends JpaRepository<Topic, Long> {


    @Query("select t from Topic t")
    List<TopicDto> getList();
}
