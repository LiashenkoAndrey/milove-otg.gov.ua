package gov.milove.main.dto;

import java.util.Date;

public interface NotificationDtoWithViews {

    String getMessage();

    Long getId();

    IAppUserDto getAuthor();

    Date getCreatedOn();

    Date getUpdatedOn();

    Boolean getIsViewed();

}
