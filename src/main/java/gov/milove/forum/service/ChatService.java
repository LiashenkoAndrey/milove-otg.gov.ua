package gov.milove.forum.service;

import gov.milove.forum.model.dto.ChatDto;
import gov.milove.forum.model.dto.ChatDtoWithMetadata;
import gov.milove.forum.model.dto.NewChatDto;
import gov.milove.forum.model.dto.PrivateChatDto;

import java.util.List;

public interface ChatService {

    /**
     * Adds new chat to visited users chats list
     * or updates last visited time of existing chat by user
     *
     * @param forumUserId id of forum user
     * @param chatId chat id
     */
    void addChatToVisitedUsersChatsOrUpdateIfExists(String forumUserId, Long chatId);


    /**
     * Calls when user want open or start a private chat with other user
     * @param receiverId user which started chat
     * @param senderId user that wants start chat
     * @return {@link PrivateChatDto}
     */
    PrivateChatDto findPrivateChatBetweenToUsers(String receiverId, String senderId);

    ChatDto newTopicChat(NewChatDto dto);

    List<ChatDtoWithMetadata> getUserChatsWithMetaById(String userId);
}
