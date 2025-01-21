package gov.milove.forum.controller;

import gov.milove.forum.model.dto.NewTopicDto;
import gov.milove.forum.model.dto.TopicDto;
import gov.milove.forum.model.Topic;
import gov.milove.forum.repositories.jpa.TopicRepository;
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

    private final TopicRepository topicRepo;

    @GetMapping("/forum/topic/all")
    public List<TopicDto> getAll() {
        return topicRepo.getList();
    }

    @PostMapping("/protected/forum/topic/new")
    private Topic newTopic(@RequestBody NewTopicDto dto) {
        log.info("new topic: " + dto);
        Topic saved = topicRepo.save(NewTopicDto.toDomain(dto));
        return saved;
    }

    @GetMapping("/forum/topic/id/{topicId}")
    public Topic getAll(@PathVariable Long topicId) {
        return topicRepo.findById(topicId).orElseThrow(EntityNotFoundException::new);
    }
}
