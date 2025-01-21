package gov.milove.forum.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateMessageDto {

    @NotNull
    private Long id;

    @NotNull
    private String text;

    @NotNull
    private Long chatId;
}
