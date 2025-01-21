package gov.milove.forum.model.dto;

import gov.milove.forum.model.Topic;
import lombok.Getter;

@Getter
public class NewTopicDto {
    private String name;
    private String description;

    public static Topic toDomain(NewTopicDto dto) {
        return new Topic(dto.getName(), dto.getDescription());
    }
}
