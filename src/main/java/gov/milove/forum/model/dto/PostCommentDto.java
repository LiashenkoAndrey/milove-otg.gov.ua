package gov.milove.forum.model.dto;

import gov.milove.forum.model.ForumUserDto;

import java.util.Date;

public interface PostCommentDto {

    Long getId();

    String getText();

    ForumUserDto getAuthor();

    Date getCreatedOn();
}
