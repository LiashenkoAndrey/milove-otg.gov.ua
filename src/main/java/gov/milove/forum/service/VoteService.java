package gov.milove.forum.service;

import gov.milove.forum.model.vote.Vote;

public interface VoteService {

    Long deleteById(Long id);

    Vote update(Vote vote);

    Vote create(Vote vote);
}
