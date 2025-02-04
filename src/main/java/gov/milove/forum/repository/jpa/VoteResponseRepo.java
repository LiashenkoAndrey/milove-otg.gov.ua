package gov.milove.forum.repository.jpa;

import gov.milove.forum.domain.VoteResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface VoteResponseRepo extends JpaRepository<VoteResponse, Long> {

    @Modifying
    @Transactional
    void deleteAllByVoteId(Long voteId);
}
