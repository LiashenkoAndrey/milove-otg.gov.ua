package gov.milove.forum.service.impl;

import gov.milove.forum.dto.FileSavedDto;
import gov.milove.forum.domain.File;
import gov.milove.forum.domain.MessageFile;
import gov.milove.forum.repository.jpa.MessageFileRepo;
import gov.milove.forum.service.FileService;
import gov.milove.forum.service.MessageFileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Log4j2
@RequiredArgsConstructor
public class MessageFileServiceImpl implements MessageFileService {

    private final FileService fileService;

    private final MessageFileRepo messageFileRepo;

    private final SimpMessagingTemplate messagingTemplate;


    @Override
    public Long deleteById(Long id) {
        messageFileRepo.deleteById(id);

        Integer amountOfUsed = messageFileRepo.amountOfUsed(id);

        if (amountOfUsed != 0 && amountOfUsed > 1) {
            MessageFile messageFile = messageFileRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
            fileService.delete(messageFile.getFile());
        }

        return id;
    }

    @Override
    public MessageFile save(MultipartFile file, Long messageId) {
        File savedFile = fileService.save(file);
        MessageFile messageFile = new MessageFile(savedFile, messageId);
      return messageFileRepo.save(messageFile);
    }

    @Override
    public void saveFiles(MultipartFile[] files, Long messageId, Long chatId) {
        for(MultipartFile file : files) {
            File savedFile = fileService.save(file);
            messageFileRepo.save(new MessageFile(savedFile, messageId));


            FileSavedDto fileSavedDto = FileSavedDto.builder()
                    .messageId(messageId)
                    .isLarge(savedFile.getIsLarge())
                    .name(savedFile.getName())
                    .mongoId(savedFile.getMongoFileId())
                    .build();

            notifyAllThatFileSaved(fileSavedDto, chatId);
        }
    }

    @Override
    public void saveFile(MultipartFile file, Long messageId, Long chatId) {
        File savedFile = fileService.save(file);

        FileSavedDto fileSavedDto = FileSavedDto.builder()
                .messageId(messageId)
                .isLarge(savedFile.getIsLarge())
                .name(savedFile.getName())
                .mongoId(savedFile.getMongoFileId())
                .build();

        notifyAllThatFileSaved(fileSavedDto, chatId);
    }

    private void notifyAllThatFileSaved(FileSavedDto dto, Long chatId) {
        String destin = "/chat/"+ chatId +"/messageFileSaved";
        messagingTemplate.convertAndSend(destin, dto);
    }

}
