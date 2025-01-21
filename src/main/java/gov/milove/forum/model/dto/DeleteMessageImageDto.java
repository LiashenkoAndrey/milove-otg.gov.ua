package gov.milove.forum.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DeleteMessageImageDto {

    private Long messageId;

    private String imageId;

    private Long chatId;
}
