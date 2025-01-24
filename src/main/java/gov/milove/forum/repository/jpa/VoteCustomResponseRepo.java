package gov.milove.forum.repository.jpa;

import gov.milove.forum.domain.VoteCustomResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface VoteCustomResponseRepo extends JpaRepository<VoteCustomResponse, Long> {

    @Transactional
    @Modifying
    void deleteAllByVoteId(Long voteId);
}
