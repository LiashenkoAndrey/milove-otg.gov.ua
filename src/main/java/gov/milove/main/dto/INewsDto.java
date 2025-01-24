package gov.milove.main.dto;

import gov.milove.main.domain.NewsImage;
import gov.milove.main.domain.NewsType;

import java.time.LocalDateTime;
import java.util.List;

public interface INewsDto {

    String getDescription();

    LocalDateTime getDateOfPublication();

    Long getViews();

    NewsType getNewsType();

    String getImage_id();

    Long getCommentsAmount();

    List<NewsImage> getImages();

    Long getId();
}
