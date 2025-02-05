package gov.milove.main.controller.impl;

import gov.milove.main.controller.NewsController;
import gov.milove.main.domain.News;
import gov.milove.main.domain.NewsImage;
import gov.milove.main.domain.NewsType;
import gov.milove.main.dto.INewsDto;
import gov.milove.main.dto.NewsDtoWithImageAndType;
import gov.milove.main.dto.NewsPageDto;
import gov.milove.main.exception.IllegalParameterException;
import gov.milove.main.exception.NewsNotFoundException;
import gov.milove.main.repository.jpa.NewsRepository;
import gov.milove.main.repository.jpa.NewsTypeRepo;
import gov.milove.main.service.NewsImagesService;
import gov.milove.main.service.NewsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class NewsControllerImpl implements NewsController {

    private final NewsService newsService;
    private final NewsRepository newsRepository;
    private final NewsTypeRepo newsTypeRepo;
    private final NewsImagesService newsImagesService;

    @Override
    public List<INewsDto> newsAll(Integer page, Integer size) {

        return newsRepository.findDistinctBy(PageRequest.of(page, size).withSort(Sort.Direction.DESC, "dateOfPublication")).toList();

    }

    @Override
    public NewsType saveNewsType(NewsType newsType) {
        return newsTypeRepo.save(newsType);
    }

    @Override
    @Transactional
    public ResponseEntity<Long> newNews(String title, String text, LocalDateTime dateOfPublication, LocalDateTime dateOfPostponedPublication, MultipartFile[] images, Long newsTypeId) {
        log.info("CREATE NEWS");
        log.info("images length = = {}", images.length);
        log.info("title = {}, dateOfPublication = {}, dateOfPostponedPublication = {}", title, dateOfPublication, dateOfPostponedPublication);
        log.info("newsType = {}", newsTypeId);

        NewsType newsType = newsTypeId > 0 ? newsTypeRepo.findById(newsTypeId).orElseThrow(EntityNotFoundException::new) : null;

        News newNews = News.builder().description(title).main_text(text).dateOfPublication(dateOfPublication).newsType(newsType).views(0L).build();

        News news = newsService.save(newNews, images, dateOfPostponedPublication);

        return ResponseEntity.status(HttpStatus.CREATED).body(news.getId());
    }

    @Override
    public void deleteNewsTypeById(Long id) {
        if (id <= 0) throw new IllegalParameterException("Id must be higher than zero");
        newsTypeRepo.deleteById(id);
    }


    @Override
    public List<NewsType> getNewsTypes() {
        return newsTypeRepo.findAll();
    }


    @Override
    public ResponseEntity<Long> deleteNewsById(Long id) {
        if (id <= 0) throw new IllegalParameterException("Id must be higher than zero");
        if (id <= 0) throw new IllegalParameterException("Id must be higher than zero");
        newsService.deleteById(id);
        newsService.deleteById(id);
        return ResponseEntity.accepted().body(id);
    }


    public ResponseEntity<String> deleteNewsImageById(String id) {
        if (!ObjectId.isValid(id)) throw new IllegalParameterException("Image id hex string is not valid");
        newsService.deleteNewsImageById(id);
        return ResponseEntity.accepted().body(id);
    }

    @Override
    public ResponseEntity<Long> updateNews(Long id, String title, String text, LocalDateTime dateOfPublication) {
        log.info("title = {}, text = {}, date = {}", title, text, dateOfPublication);
        News news = newsRepository.findById(id).orElseThrow(NewsNotFoundException::new);
        news.setDescription(title);
        news.setDateOfPublication(dateOfPublication);
        news.setMain_text(text);
        newsRepository.save(news);
        return ResponseEntity.accepted().body(id);
    }

    @Override
    public List<NewsImage> saveNewNewsImage(Long newsId, MultipartFile[] files) {
        News news = newsRepository.findById(newsId).orElseThrow(NewsNotFoundException::new);
        List<NewsImage> newsImages = newsImagesService.saveAll(List.of(files));
        news.getImages().addAll(newsImages);
        newsRepository.save(news);
        return newsImages;
    }

    public News getNewsById(Long newsId) {
        return newsRepository.findById(newsId).orElseThrow(NewsNotFoundException::new);
    }

    @Override
    public List<INewsDto> getSimilarNewsByNewsId(Long newsId) {
        News news = newsRepository.findById(newsId).orElseThrow(NewsNotFoundException::new);

        return (news.getNewsType() != null ? newsRepository.getLastNewsDTOByNewsTypeIdWithLimit(newsId, news.getNewsType().getId(), PageRequest.of(0, 3).withSort(Sort.Direction.DESC, "dateOfPublication")).toList() : List.of());
    }

    @Override
    public NewsPageDto getLatest(Integer pageSize, Integer pageNumber) {
        Page<INewsDto> newsDtos = newsRepository.findDistinctBy(PageRequest.ofSize(pageSize).withPage(pageNumber).withSort(Sort.Direction.DESC, "dateOfPublication"));
        return new NewsPageDto(newsDtos);
    }

    @Override
    public ResponseEntity<String> updateNews(NewsDtoWithImageAndType news) {
        newsService.update(news);
        return new ResponseEntity<>("Оновлення успішне", HttpStatus.OK);
    }

    @Override
    public Long incrementViews(Long id) {
        newsRepository.incrementViews(id);
        return id;
    }
}
