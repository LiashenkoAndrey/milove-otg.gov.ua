package gov.milove.domain.forum;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@Table(schema = "forum")
public class Chat {

    public Chat(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Chat(String name, String description, String picture) {
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String picture;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ForumUser owner;

    private Boolean isPrivate;

    @Formula("(select count(*) from forum.message m where m.chat_id = id)")
    private Long totalMessagesAmount;
}
