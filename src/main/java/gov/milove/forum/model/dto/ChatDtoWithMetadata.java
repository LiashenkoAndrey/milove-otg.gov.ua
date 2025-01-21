package gov.milove.forum.model.dto;

import gov.milove.forum.model.chat.Chat;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ChatDtoWithMetadata  {

    private Chat chat;

    private ChatMetadata chatMetadata;

    private PrivateChatMetadata privateChatMetadata;

}
