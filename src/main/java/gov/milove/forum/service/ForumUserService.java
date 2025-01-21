package gov.milove.forum.service;

import gov.milove.forum.model.dto.NewForumUserDto;
import gov.milove.forum.model.ForumUser;

public interface ForumUserService {

    ForumUser saveNewUser(NewForumUserDto dto, String appUserId);


}
