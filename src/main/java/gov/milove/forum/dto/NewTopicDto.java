package gov.milove.forum.dto;

import gov.milove.forum.domain.Topic;
import lombok.Getter;

@Getter
public class NewTopicDto {
    private String name;
    private String description;

    public static Topic toDomain(NewTopicDto dto) {
        return new Topic(dto.getName(), dto.getDescription());
    }
}
