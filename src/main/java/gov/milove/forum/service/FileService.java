package gov.milove.forum.service;

import gov.milove.forum.model.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    File save(MultipartFile file);

    void delete(File file);

}
