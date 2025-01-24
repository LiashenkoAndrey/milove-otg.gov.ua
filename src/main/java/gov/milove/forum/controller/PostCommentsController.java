package gov.milove.forum.controller;

import gov.milove.forum.dto.PostCommentDto;
import gov.milove.forum.domain.PostComment;
import gov.milove.forum.repository.jpa.ForumUserRepo;
import gov.milove.forum.repository.jpa.PostCommentRepo;
import gov.milove.main.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class PostCommentsController {

    private final PostCommentRepo commentRepo;
    private final ForumUserRepo userRepo;

    @PostMapping("/protected/forum/post/{postId}/comment/new")
    public PostCommentDto newPostComment(@RequestParam String encodedForumUserId,
                                         @RequestParam String text,
                                         @PathVariable Long postId) {
        log.info("new post comment  postId={}, text={}" ,postId, text);
        String forumUserId = Util.decodeUriComponent(encodedForumUserId);
        PostComment comment = PostComment.builder()
                .author(userRepo.getReferenceById(forumUserId))
                .text(text)
                .build();
        PostComment saved = commentRepo.save(comment);
        return commentRepo.getPostDtoById(saved.getId());
    }
}
