package gov.milove.main.service;

import gov.milove.main.domain.News;
import gov.milove.main.dto.NewsDtoWithImageAndType;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public interface NewsService {

    News save(News news, MultipartFile[] images, LocalDateTime dateOfPostponedPublication);

    void deleteById(Long id);

    void update(NewsDtoWithImageAndType news);

    void deleteNewsImageById(String mongoId);

}
