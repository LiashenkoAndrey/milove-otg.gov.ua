package gov.milove.forum.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DeleteMessageDto {

    private Long messageId;

    private Long chatId;
}
