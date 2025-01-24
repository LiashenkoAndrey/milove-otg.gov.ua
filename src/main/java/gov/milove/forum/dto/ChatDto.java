package gov.milove.forum.dto;

import gov.milove.forum.domain.ForumUserDto;

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
