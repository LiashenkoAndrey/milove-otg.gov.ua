package gov.milove.forum.controller;

import gov.milove.forum.dto.ForumUserDto;
import gov.milove.forum.dto.UpdateUserOnlineStatusDto;
import gov.milove.forum.dto.NewForumUserDto;
import gov.milove.forum.domain.ForumUser;
import gov.milove.forum.exception.ForumUserNotFoundException;
import gov.milove.forum.repository.jpa.ForumUserRepo;
import gov.milove.main.util.Util;
import gov.milove.forum.service.ForumUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ForumUserController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ForumUserRepo forumUserRepo;
    private final ForumUserService forumUserService;


    @MessageMapping("/user/startTyping")
    public void handleUserStartTyping(@Valid @Payload ForumUserDto dto) {
        log.info("user start typing, " + dto);
        messagingTemplate.convertAndSend("/chat/"+ dto.getChatId() +"/typingUsers", dto);
    }

    @GetMapping("/forum/users")
    public List<ForumUser> getAll() {
        return forumUserRepo.findAll();
    }

    @PostMapping("/protected/forum/user/new")
    public ForumUser newForumUser(@Valid @ModelAttribute NewForumUserDto dto, @RequestParam String encodedAppUserId) {
        log.info("new user, dto - {} ", dto);
        String decodedAppUserId = Util.decodeUriComponent(encodedAppUserId);
        ForumUser user = forumUserService.saveNewUser(dto, decodedAppUserId);
        log.info("user - {}" , user);
        return user;
    }

    @GetMapping("/protected/forum/user/{encodedId}")
    public JSONObject getForumUserById(@PathVariable String encodedId) {
        String decodedId = Util.decodeUriComponent(encodedId);
        JSONObject jsonObject = new JSONObject();
        if (forumUserRepo.existsById(decodedId)) {
            ForumUser user = forumUserRepo.findById(decodedId).orElseThrow(ForumUserNotFoundException::new);

            jsonObject.put("forumUser", user);
            jsonObject.put("isRegistered", true);

        } else {
            jsonObject.put("isRegistered", false);
        }
        return jsonObject;
    }

    @MessageMapping("/forumUser/isOnlineStatus")
    public void notifyThatUserIsOnline(@Valid @Payload UpdateUserOnlineStatusDto dto) {
        log.info("notify that user is online, " + dto);
        dto.setDate(new Date());
        messagingTemplate.convertAndSend("/chat/user/"  + dto.getUserIdThatNeedsNotification() +"/onlineStatus", dto);
        forumUserRepo.updateUserOnlineStatusById(dto.getUserIdThatOnlineStatusNeedsToBeUpdated(), dto.getIsOnline());
    }


    @GetMapping("/protected/forum/user/isRegistered/id/{encodedUserId}")
    public boolean isRegistered(@PathVariable String encodedUserId) {
        String decodedUserId = Util.decodeUriComponent(encodedUserId);
        log.info("user id - {}" , decodedUserId);
        return forumUserRepo.existsById(decodedUserId);
    }

    @MessageMapping("/user/stopTyping")
    public void handleUserStopTyping(@Payload ForumUserDto dto) {
        log.info("User has stopped typing, " + dto);
        messagingTemplate.convertAndSend("/chat/"+ dto.getChatId() +"/userStopTyping", dto);
    }

    @GetMapping("/forum/activeUsers")
    public Integer getActiveUsersAmount() {
        return forumUserRepo.getActiveUsersAmount();
    }
}
