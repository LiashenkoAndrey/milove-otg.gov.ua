package gov.milove.forum.model.dto;

import gov.milove.forum.model.ForumUserDto;

public interface ChatDto {

    Long getId();

    String getName();

    String getDescription();

    String getPicture();

    String getCreatedOn();

    String getIsPrivate();

    ForumUserDto getOwner();

    Long getTotalMessagesAmount();

    Long getTotalMembersAmount();
}
