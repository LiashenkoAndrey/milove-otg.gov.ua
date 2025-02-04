package gov.milove.controllers.abstr;

import gov.milove.domain.NewsComment;
import gov.milove.domain.dto.NewCommentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "News comment controller")
@RequestMapping("/api")
public interface INewsCommentController {

    @Operation(summary = "Get the news comment list")
    @GetMapping("/news/{newsId}/comments")
    List<NewsComment> getComments(@PathVariable Long newsId);

    @Operation(summary = "Create a new comment")
    @PostMapping("/news/comment/new")
    NewsComment newComment(@RequestBody NewCommentDto dto);

    @Operation(summary = "Delete a comment")
    @DeleteMapping("/protected/news/comment/{id}/delete")
    Long delete(@PathVariable Long id);
}
