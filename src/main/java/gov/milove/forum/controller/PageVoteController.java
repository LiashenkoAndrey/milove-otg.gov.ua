package gov.milove.forum.controller;

import gov.milove.forum.domain.Vote;
import gov.milove.forum.repository.jpa.VoteRepo;
import gov.milove.main.util.Util;
import gov.milove.forum.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class PageVoteController {

    private final VoteService voteService;
    private final VoteRepo voteRepo;

    @PostMapping("/protected/forum/pageVote/new")
    public Long createNewVote(@RequestBody Vote vote) {
        log.info("Create a new vote {}", vote);
        vote.setAuthorId(Util.decodeUriComponent(vote.getAuthorId()));
        return voteService.create(vote)
                .getId();
    }

    /**
     * Gets latest vote
     * If no votes return null
     * @return null
     */
    @GetMapping("/forum/pageVote/latest")
    public Vote getLatestVote() {
        return voteRepo.getLatestPageVote()
                .orElse(null);
    }

}
