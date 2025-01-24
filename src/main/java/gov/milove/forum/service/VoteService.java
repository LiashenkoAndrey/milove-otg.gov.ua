package gov.milove.forum.service;

import gov.milove.forum.domain.Vote;

public interface VoteService {

    Long deleteById(Long id);

    Vote update(Vote vote);

    Vote create(Vote vote);
}
