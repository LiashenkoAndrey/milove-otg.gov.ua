package gov.milove.forum.domain;

import java.util.Date;

public interface ForumUserDto {

    String getId();

    Date getRegisteredOn();

    String getNickname();

    String getAboutMe();

    String getAvatar();

    Boolean getIsVerified();
}
