package gov.milove.forum.service.impl;

import gov.milove.forum.service.MessageImageService;
import gov.milove.forum.model.dto.MessageImageDto;
import gov.milove.forum.model.message.MessageImage;
import gov.milove.forum.model.mongo.MongoMessageImage;
import gov.milove.exceptions.ImageNotFoundException;
import gov.milove.forum.repositories.jpa.MessageRepo;
import gov.milove.forum.repositories.mongo.MessageImageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageImageServiceImpl implements MessageImageService {

    private final MessageImageRepo messageImageRepo;

    private final MessageRepo messageRepo;

    @Override
    public List<MessageImage> saveImages(List<MessageImageDto> dtoList) {
        return dtoList.stream()
                .map(MessageImageDto::toEntity)
                .map(this::saveIfNotExistOrGetSaved)
                .map((e) -> new MessageImage(e.getId(), e.getHashCode()))
                .toList();
    }

    @Override
    public void deleteImagesIfNotUsedMoreThenOneTime(List<MessageImage> images) {
        for (MessageImage m : images) {
            deleteImageIfNotUsedMoreThenOneTime(m.getImageId());
        }
    }

    @Override
    public void deleteImageFromMessage(String imageId, Long messageId) {
        if (!messageRepo.messageImageIsUsedMoreThenOneTime(imageId)) {
            messageImageRepo.deleteById(imageId);
        }
        messageRepo.deleteMessageImage(imageId, messageId);
    }

    public void deleteImageIfNotUsedMoreThenOneTime(String imgId) {
        if (!messageRepo.messageImageIsUsedMoreThenOneTime(imgId)) {
            messageImageRepo.deleteById(imgId);
        }
    }

    private MongoMessageImage saveIfNotExistOrGetSaved(MongoMessageImage image) {
        Example<MongoMessageImage> example = Example.of(new MongoMessageImage(image.getHashCode()));
        if (messageImageRepo.exists(example)) {
            return messageImageRepo.findOne(example).orElseThrow(ImageNotFoundException::new);
        }
        return messageImageRepo.save(image);
    }
}
