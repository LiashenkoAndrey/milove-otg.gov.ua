package gov.milove.forum.dto;

import gov.milove.forum.domain.ForumUserDto;

import java.util.Date;

public interface PostCommentDto {

    Long getId();

    String getText();

    ForumUserDto getAuthor();

    Date getCreatedOn();
}
