package gov.milove.forum.service;

import gov.milove.forum.dto.NewForumUserDto;
import gov.milove.forum.domain.ForumUser;

public interface ForumUserService {

    ForumUser saveNewUser(NewForumUserDto dto, String appUserId);


}
