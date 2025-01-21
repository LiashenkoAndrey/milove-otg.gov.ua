package gov.milove.forum.model.dto;

import gov.milove.forum.model.ForumUserDto;

import java.util.Date;

public interface UserStoryDto {

    Long getId();

    ForumUserDto getAuthor();

    String getText();

    String getImageId();

    Date getCreatedOn();
}
