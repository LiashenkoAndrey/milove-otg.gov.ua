package gov.milove.forum.model.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class UpdateUserOnlineStatusDto {

    private String userIdThatOnlineStatusNeedsToBeUpdated;

    private String userIdThatNeedsNotification;

    private Boolean isOnline;

    private Date date;
}
