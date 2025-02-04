package gov.milove.forum.dto;

import gov.milove.forum.domain.ForumUserDto;

import java.util.Date;

public interface UserStoryDto {

    Long getId();

    ForumUserDto getAuthor();

    String getText();

    String getImageId();

    Date getCreatedOn();
}
