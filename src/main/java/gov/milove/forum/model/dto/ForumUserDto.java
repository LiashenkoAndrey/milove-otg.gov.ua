package gov.milove.forum.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class ForumUserDto {

    @NotNull
    private Long chatId;

    @NonNull
    private String id;

    @NotNull
    private String name;
}
