package gov.milove.forum.dto;

import gov.milove.forum.domain.Chat;
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
