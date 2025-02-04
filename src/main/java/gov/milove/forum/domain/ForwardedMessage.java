package gov.milove.forum.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Table(schema = "forum", name = "forwarded_message")
public class ForwardedMessage {

    @Id
    private Long messageId;

    @OneToOne
    @JoinColumn(name ="forwarded_message_id")
    private Message forwardedMessage;

}
