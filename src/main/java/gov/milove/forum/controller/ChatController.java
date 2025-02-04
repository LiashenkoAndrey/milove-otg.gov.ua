package gov.milove.forum.controller;

import gov.milove.forum.dto.ChatDto;
import gov.milove.forum.dto.ChatDtoWithMetadata;
import gov.milove.forum.dto.ChatMetadataDto;
import gov.milove.forum.dto.NewChatDto;
import gov.milove.forum.dto.PrivateChatDto;
import gov.milove.forum.repository.jpa.ChatRepo;
import gov.milove.forum.service.ChatService;
import gov.milove.main.util.Util;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatController {

    private final ChatRepo chatRepo;
    private final ChatService chatService;

    @GetMapping("/forum/chat/all")
    public List<ChatDto> getAllByTopicId(@RequestParam(required = false) Long topicId) {
        return chatRepo.findByTopicId(topicId);
    }

    @GetMapping("/protected/forum/user/{encodedUserId}/chats")
    public List<ChatDtoWithMetadata> getUserVisitedChats(@PathVariable String encodedUserId) {
        return chatService.getUserChatsWithMetaById(Util.decodeUriComponent(encodedUserId));
    }

    /**
     * Gets chat by id
     * @param chatId chat id
     * @param encodedUserId encoded forum user id
     * @return {@link ChatDto} DTO of chat entity
     */
    @GetMapping("/forum/chat/id/{chatId}")
    public ChatDto getChatById(@PathVariable Long chatId,
                               @RequestParam(required = false) String encodedUserId) {
        log.info("getChatById userId = {}", encodedUserId);
        chatService.addChatToVisitedUsersChatsOrUpdateIfExists(Util.decodeUriComponent(encodedUserId), chatId);
        return chatRepo.findChatById(chatId);
    }


    /**
     * Frontend calls this endpoint when user opens a private chat with other forum user
     * If chat doesn't exist it will be created
     *
     * @param receiverId the user with whom the chat was opened
     * @param senderId   user witch sends messages
     * @return PrivateChatDto
     */
    @PostMapping("/protected/forum/user/{receiverId}/chat")
    public PrivateChatDto findOrCreatePrivateBetweenTwoForumUser(@PathVariable String receiverId,
                                                                 @RequestParam String senderId) {

        String receiverIdDecoded = Util.decodeUriComponent(receiverId);
        String senderIdDecoded = Util.decodeUriComponent(senderId);
        log.info("findOrCreatePrivateBetweenTwoForumUser senderId = {}, receiverId= {}", senderIdDecoded, receiverIdDecoded);

        PrivateChatDto privateChatDto = chatService.findPrivateChatBetweenToUsers(receiverIdDecoded, senderIdDecoded);

        // add a new private chat to user visited chat list or update last visited time by user
        chatService.addChatToVisitedUsersChatsOrUpdateIfExists(senderIdDecoded, privateChatDto.getId());

        return privateChatDto;
    }

    @GetMapping("/forum/chat/id/{chatId}/metadata")
    public ChatMetadataDto getChatMetadata(@PathVariable Long chatId, @RequestParam String userId) {
        return chatRepo.getChatMetadata(chatId, Util.decodeUriComponent(userId));
    }


    @PostMapping("/protected/forum/chat/new")
    public ChatDto newChat(@Valid @RequestBody NewChatDto dto) {
        return chatService.newTopicChat(dto);
    }


}
