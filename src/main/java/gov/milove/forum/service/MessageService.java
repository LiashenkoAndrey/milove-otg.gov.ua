package gov.milove.forum.service;

import gov.milove.forum.dto.ForwardMessagesDto;
import gov.milove.forum.dto.MessageDto;
import gov.milove.forum.dto.MessageRequestDto;
import gov.milove.forum.domain.Message;

import java.util.List;

public interface MessageService {

    Message saveNewMessage(MessageDto messageDto);

    void forwardMessages(ForwardMessagesDto dto);

    List<Message> getMessages(MessageRequestDto dto);

    Long deleteById(Long id);

    Message saveMessage(MessageDto messageDto);
}
