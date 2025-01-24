package gov.milove.main.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NewNotificationDto {

    private String message;

    private String text;

    private String authorId;
}
