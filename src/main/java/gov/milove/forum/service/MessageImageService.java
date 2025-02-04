package gov.milove.forum.service;

import gov.milove.forum.dto.MessageImageDto;
import gov.milove.forum.domain.MessageImage;

import java.util.List;

public interface MessageImageService {


    List<MessageImage> saveImages(List<MessageImageDto> dtoList);

    void deleteImagesIfNotUsedMoreThenOneTime(List<MessageImage> images);

    void deleteImageFromMessage(String imageId, Long messageId);
}
