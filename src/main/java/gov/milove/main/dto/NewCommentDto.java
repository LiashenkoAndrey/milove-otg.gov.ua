package gov.milove.main.dto;

import gov.milove.main.domain.NewsCommenter;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewCommentDto {


    private Long newsId;
    private String appUserId;
    private NewsCommenter newsCommenter;
    private Long commentId;

    @NotNull
    private String text;
}
