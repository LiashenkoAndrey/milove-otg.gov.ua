package gov.milove.forum.repository.jpa;

import gov.milove.forum.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VoteRepo extends JpaRepository<Vote, Long> {

    @Query("from Vote v inner join PageVote pv on v.id = pv.voteId order by v.createdOn desc limit 1")
    Optional<Vote> getLatestPageVote();
}
