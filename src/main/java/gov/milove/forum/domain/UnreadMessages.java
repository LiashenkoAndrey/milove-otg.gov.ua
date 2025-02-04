package gov.milove.forum.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "forum")
public class UnreadMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatId;

    private String userId;
    private Long messageId;
}
