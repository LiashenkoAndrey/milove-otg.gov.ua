package gov.milove.forum.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ChatMetadata {

    private Long last_read_message_id;

    private Long unread_messages_count;

    private Boolean is_member;
}
