package gov.milove.controllers.abstr;

import gov.milove.domain.News;
import gov.milove.domain.NewsImage;
import gov.milove.domain.NewsType;
import gov.milove.domain.dto.INewsDto;
import gov.milove.domain.dto.NewsDtoWithImageAndType;
import gov.milove.domain.dto.NewsPageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "News controller")
@Validated
@RequestMapping("/api")
public interface INewsController {

    @Operation(summary = "Get news page")
    @GetMapping("/news/all")
    List<INewsDto> newsAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer size);

    @Operation(summary = "Add news type")
    @PostMapping("/protected/newsType/new")
    NewsType saveNewsType(@Validated @RequestBody NewsType newsType);

    @Operation(summary = "Add news")
    @PostMapping("/protected/news/new")
    @Transactional
    ResponseEntity<Long> newNews(@RequestParam @NotBlank @Size(max = 300) String title,
                                 @RequestParam @NotBlank String text,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateOfPublication,
                                 @RequestParam(required = false) @Future @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateOfPostponedPublication,
                                 @RequestParam @NotEmpty @Size(max = 20) MultipartFile[] images,
                                 @RequestParam Long newsTypeId
    );

    @Operation(summary = "Delete news type")
    @DeleteMapping("/protected/newsType/{id}/delete")
    void deleteNewsTypeById(@PathVariable Long id);

    @Operation(summary = "Get news type")
    @GetMapping("/protected/news-types")
    List<NewsType> getNewsTypes();

    @Operation(summary = "Delete news by id")
    @DeleteMapping("/protected/news/{id}/delete")
    ResponseEntity deleteNewsById(@PathVariable Long id);

    @Operation(summary = "Delete news image")
    @DeleteMapping("/protected/news/image/{id}/delete")
    ResponseEntity<String> deleteNewsImageById(@PathVariable String id);

    @Operation(summary = "Update news")
    @PutMapping("/protected/news/{id}/update")
    ResponseEntity<Long> updateNews(@PathVariable Long id,
                                    @RequestParam @NotBlank @Size(max = 300) String title,
                                    @RequestParam @NotBlank String text,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateOfPublication);

    @Operation(summary = "Add news image")
    @PostMapping("/protected/news/{newsId}/image/new")
    List<NewsImage> saveNewNewsImage(@PathVariable Long newsId,
                                     @RequestParam("images") MultipartFile[] files);

    @Operation(summary = "Get news by id")
    @GetMapping("/news/{newsId}")
    News getNewsById(@PathVariable("newsId") Long news_id);

    @Operation(summary = "Get similar news by id")
    @GetMapping("/news/{newsId}/similar")
    List<INewsDto> getSimilarNewsByNewsId(@PathVariable Long newsId);

    @Operation(summary = "Get the most recent news")
    @GetMapping("/news/latest")
    NewsPageDto getLatest(@RequestParam(defaultValue = "6", required = false) @Min(1) Integer pageSize,
                          @RequestParam(defaultValue = "0", required = false) Integer pageNumber);

    @Operation(summary = "Update news")
    @PostMapping("/news/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<String> updateNews(@ModelAttribute NewsDtoWithImageAndType news);

    @Operation(summary = "Increment views count")
    @PostMapping("/news/{id}/incrementViews")
    Long incrementViews(@PathVariable Long id);
}
