package gov.milove.services.forum.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import gov.milove.domain.forum.File;
import gov.milove.domain.forum.MongoFile;
import gov.milove.exceptions.FileNotFoundException;
import gov.milove.exceptions.ServiceException;
import gov.milove.repositories.forum.FileRepo;
import gov.milove.repositories.mongo.MongoFileRepo;
import gov.milove.services.forum.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static gov.milove.util.Util.getFileFormat;

@Service
@RequiredArgsConstructor
@Log4j2
public class FileServiceImpl implements FileService {

    private final MongoFileRepo mongoFileRepo;
    private final FileRepo fileRepo;

    private final GridFsTemplate gridFsTemplate;

    private static final long MAX_DOCUMENT_SIZE = 16777216L;




    private File saveLarge(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();

            DBObject meta = new BasicDBObject();
            meta.put("contentType", file.getContentType());
            log.info("save large file, name= {}, meta = {}", file.getOriginalFilename(), meta);

            ObjectId id = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), meta);

            log.info("large file save ok, id={}", id);
            log.info("file getOriginalFilename = {}", file.getOriginalFilename());

            File newFile = buildFile(file, bytes, id.toHexString());
            File saved = fileRepo.save(newFile);
            log.info("message file save ok = {}", saved);
            return saved;
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error(e);
            throw new ServiceException("can't save large file", e);
        }
    }

    private File saveSmall(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            MongoFile mongoFile = MongoFile.builder()
                    .file(new Binary(bytes))
                    .contentType(file.getContentType())
                    .name(file.getOriginalFilename())
                    .size(file.getSize())
                    .build();

            MongoFile savedMongoFile = mongoFileRepo.save(mongoFile);

            log.info("file getOriginalFilename = {}", file.getOriginalFilename());
            File newFile = buildFile(file, bytes, savedMongoFile.getId());

            File saved = fileRepo.save(newFile);
            log.info("saved file = {}", saved);
            return saved;
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error(e);
            throw new ServiceException("can't save file", e);
        }
    }

    private File buildFile(MultipartFile file, byte[] bytes, String mongoFileId) throws NoSuchAlgorithmException {
        String hashCode = getFileHashCode(bytes);
        String format = getFileFormat(file.getOriginalFilename());
        return File.builder()
                .name(file.getOriginalFilename())
                .size(file.getSize())
                .mongoFileId(mongoFileId)
                .hashCode(hashCode)
                .format(format)
                .isLarge(false)
                .build();
    }

    @Override
    public File save(MultipartFile file) {
        try {
            String hashCode = getFileHashCode(file.getBytes());
            log.info("find file, name = {}, hash = {}", file.getOriginalFilename(), hashCode);
            Optional<File> saved = fileRepo.findFirstByHashCode(hashCode);
            log.info("found file = {}", saved);
            if (saved.isPresent()) {
                File found = saved.get();
                found.setName(file.getOriginalFilename());
                return found;
            } else {
                log.info("file size = {}, max doc size = {}, fileSize > Max {}", file.getSize(), MAX_DOCUMENT_SIZE, file.getSize() >= MAX_DOCUMENT_SIZE);
                if (file.getSize() >= MAX_DOCUMENT_SIZE) {
                    return saveLarge(file);
                }
                return saveSmall(file);
            }

        } catch (IOException | NoSuchAlgorithmException e) {
            log.error(e);
            throw new ServiceException("can't save file", e);
        }
    }

    @Override
    public void delete(File file) {
        mongoFileRepo.deleteById(file.getMongoFileId());
        fileRepo.deleteById(file.getId());
    }


    private String getFileHashCode(byte[] fileBytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(fileBytes);
        byte[] hashBytes = md.digest();

        StringBuilder hashStringBuilder = new StringBuilder();
        for (byte hashByte : hashBytes) {
            hashStringBuilder.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
        }
        return hashStringBuilder.toString();

    }

}
