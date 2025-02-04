package gov.milove.controllers;


import gov.milove.controllers.abstr.INewsCommentController;
import gov.milove.domain.NewsComment;
import gov.milove.domain.NewsCommenter;
import gov.milove.domain.User;
import gov.milove.domain.dto.NewCommentDto;
import gov.milove.repositories.jpa.AppUserRepo;
import gov.milove.repositories.jpa.NewsCommentRepo;
import gov.milove.repositories.jpa.NewsCommenterRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static gov.milove.util.Util.decodeUriComponent;

@RestController
@Log4j2
@RequestMapping("/api")
@RequiredArgsConstructor
public class NewsCommentController implements INewsCommentController {

    private final AppUserRepo appUserRepo;
    private final NewsCommentRepo commentRepo;
    private final NewsCommenterRepo newsCommenterRepo;

    @Override
    public List<NewsComment> getComments(Long newsId) {
        return commentRepo.findAllByNewsIdOrderByCreatedOnDesc(newsId);
    }

    @Override
    public NewsComment newComment(NewCommentDto dto) {
        String appUserId =  decodeUriComponent(dto.getAppUserId());
        log.info("new comment, newsId = {}, appUserId = {}, newsCommenter = {}, commentId = {}, text = {}", dto.getNewsId(), appUserId, dto.getNewsCommenter(), dto.getCommentId(), dto.getText());
        User author;

        if (appUserId == null) {
            if (dto.getNewsCommenter() == null) throw new IllegalArgumentException("authorId or newCommenter is not present");
            NewsCommenter dtoCommenter = dto.getNewsCommenter();
            dtoCommenter.setId(UUID.randomUUID().toString());
            NewsCommenter savedCommenter = newsCommenterRepo.save(dtoCommenter);
            log.info("saved commenter = {}", savedCommenter);
            author = savedCommenter;
        } else {
            log.info("user is registered, id = {}", appUserId);
            author = appUserRepo.getReferenceById(appUserId);
        }
        log.info("author = {}", author);

        if ((dto.getNewsId() == null && dto.getCommentId() == null) || (dto.getNewsId() != null && dto.getCommentId() != null))
            throw new IllegalArgumentException("It needs to send only a newsId or a comment id");

        NewsComment comment = NewsComment.builder()
                .author(author)
                .newsId(dto.getNewsId())
                .commentId(dto.getCommentId())
                .text(dto.getText())
                .build();
        NewsComment saved = commentRepo.save(comment);
        log.info("saved comment {}", saved);
        return saved;
    }

    @Override
    public Long delete(Long id) {
        commentRepo.deleteById(id);
        return id;
    }

}
