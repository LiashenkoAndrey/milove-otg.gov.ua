package gov.milove.forum.service;

import gov.milove.forum.domain.MessageFile;
import org.springframework.web.multipart.MultipartFile;

public interface MessageFileService {


    Long deleteById(Long id);

    MessageFile save(MultipartFile file, Long messageId);

    void saveFiles(MultipartFile[] files, Long messageId, Long chatId);

    void saveFile(MultipartFile files, Long messageId, Long chatId);
}
