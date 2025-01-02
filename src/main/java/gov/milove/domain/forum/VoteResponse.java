package gov.milove.domain.forum;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(schema = "forum")
public class VoteResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String forumUserId;

    @NotNull
    private Long voteId;

    private Date respondedOn;
}
