package gov.milove.controllers.forum;

import gov.milove.domain.dto.forum.NewTopicDto;
import gov.milove.domain.forum.Topic;
import gov.milove.repositories.TopicRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
@RequiredArgsConstructor
public class TopicController {

    private final TopicRepo topicRepo;

    @GetMapping("/forum/topic/all")
    public List<Topic> getAll() {
        return topicRepo.findAll();
    }

    @PostMapping("/protected/forum/topic/new")
    private Long newTopic(@RequestBody NewTopicDto dto) {
        log.info("new topic: " + dto);
        Topic saved = topicRepo.save(NewTopicDto.toDomain(dto));
        return saved.getId();
    }

    @GetMapping("/forum/topic/id/{topicId}")
    public Topic getAll(@PathVariable Long topicId) {
        return topicRepo.findById(topicId).orElseThrow(EntityNotFoundException::new);
    }
}
