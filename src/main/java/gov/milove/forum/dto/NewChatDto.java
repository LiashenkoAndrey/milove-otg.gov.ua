package gov.milove.forum.dto;

import gov.milove.forum.domain.Chat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewChatDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    private String picture;

    @NotNull
    private String ownerId;

    @NotNull
    private Long topicId;

    public static Chat toDomain(NewChatDto dto) {
        return new Chat(dto.getName(), dto.getDescription(), dto.getPicture());
    }
}
