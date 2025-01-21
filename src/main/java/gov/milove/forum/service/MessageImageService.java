package gov.milove.forum.service;

import gov.milove.forum.model.dto.MessageImageDto;
import gov.milove.forum.model.message.MessageImage;

import java.util.List;

public interface MessageImageService {


    List<MessageImage> saveImages(List<MessageImageDto> dtoList);

    void deleteImagesIfNotUsedMoreThenOneTime(List<MessageImage> images);

    void deleteImageFromMessage(String imageId, Long messageId);
}
