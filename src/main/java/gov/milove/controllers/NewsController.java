package gov.milove.controllers;

import gov.milove.controllers.abstr.INewsController;
import gov.milove.domain.News;
import gov.milove.domain.NewsImage;
import gov.milove.domain.NewsType;
import gov.milove.domain.dto.INewsDto;
import gov.milove.domain.dto.NewsDtoWithImageAndType;
import gov.milove.domain.dto.NewsPageDto;
import gov.milove.exceptions.IllegalParameterException;
import gov.milove.exceptions.NewsNotFoundException;
import gov.milove.repositories.jpa.NewsRepository;
import gov.milove.repositories.jpa.NewsTypeRepo;
import gov.milove.services.NewsImagesService;
import gov.milove.services.NewsService;
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
public class NewsController implements INewsController {

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


//
//    public void delete() {
//        log.info("Start search");
//        List<String> documents = newsRepository.findAll().stream().map(News::getImage_id).toList();
//        log.info("50%");
//        List<MongoDocument> mongoDocuments = mongoDocumentRepo.findAll().stream()
//                .filter(mongoDocument -> !documents.contains(mongoDocument.getId())).toList();
//        log.info("Not used docs! size = {} {}", mongoDocuments.size(), mongoDocuments);
//        mongoDocumentRepo.deleteAll(mongoDocuments);
//        log.info("End search");
//    }

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
    public ResponseEntity<?> deleteNewsById(Long id) {
        if (id <= 0) throw new IllegalParameterException("Id must be higher than zero");
        newsService.deleteById(id);
        return ResponseEntity.accepted().body(id);
    }

    @Override
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

    @Override
    public News getNewsById(Long news_id) {
        return newsRepository.findById(news_id).orElseThrow(NewsNotFoundException::new);
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
