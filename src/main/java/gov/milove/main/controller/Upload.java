package gov.milove.controllers;

import gov.milove.controllers.abstr.IUpload;
import gov.milove.domain.Image;
import gov.milove.domain.MongoDocument;
import gov.milove.domain.MongoNewsImage;
import gov.milove.exceptions.ImageNotFoundException;
import gov.milove.repositories.mongo.ImageRepo;
import gov.milove.repositories.mongo.MongoDocumentRepo;
import gov.milove.repositories.mongo.NewsImagesMongoRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.Binary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


@RestController
@Log4j2
@RequiredArgsConstructor
public class Upload implements IUpload {

    private final NewsImagesMongoRepo newsImagesMongoRepo;

    private final MongoDocumentRepo mongoDocumentRepo;
    private final ImageRepo imageRepo;

    @Override
    public ResponseEntity<byte[]> getImage(String id) {
        MongoNewsImage mongoNewsImage = newsImagesMongoRepo.findById(id).orElseThrow(ImageNotFoundException::new);
        MediaType mediaType;
        if (mongoNewsImage.getContentType() == null) {
            mediaType = MediaType.IMAGE_PNG;
        } else {
            mediaType = MediaType.parseMediaType(mongoNewsImage.getContentType());
        }
        return ResponseEntity.ok().contentType(mediaType).body(mongoNewsImage.getBinaryImage().getData());
    }

    @Override
    public ResponseEntity<byte[]> getImageV2(String id, HttpServletResponse response) {
        Image image = imageRepo.findById(id).orElseThrow(ImageNotFoundException::new);
        MediaType mediaType = MediaType.parseMediaType(image.getContentType());
        String contentDisposition = "attachment; filename=\"" + image.getFilename() + "\"";
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition);
        return ResponseEntity.ok().contentType(mediaType).contentLength(image.getBinaryImage().length()).body(image.getBinaryImage().getData());
    }


    @Override
    public byte[] findDocumentByFilename(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        List<MongoDocument> mongoDocuments = mongoDocumentRepo.findByFilename(fileName);
        if (mongoDocuments.isEmpty())
            throw new EntityNotFoundException("document with name " + fileName + ", not found");
        MongoDocument mongoDocument = mongoDocuments.get(0);


        if (mongoDocuments.size() > 1) {
            log.info("MORE THEN ONE RESULT - {}", mongoDocuments);
            for (MongoDocument document : mongoDocuments.subList(1, mongoDocuments.size())) {
                log.info("delete - {} hashcode = {}, hashcode2 = {}", document, mongoDocument.getFile().hashCode(), Arrays.hashCode(mongoDocument.getFile().getData()));
                mongoDocumentRepo.deleteById(document.getId());
            }

        }
        log.info("DOWNLOAD DOCUMENT = {} hashcode = {}, hashcode2 = {}", mongoDocument, mongoDocument.getFile().hashCode(), Arrays.hashCode(mongoDocument.getFile().getData()));
        Binary mongoFile = mongoDocument.getFile();

        String encodedFilename = URLEncoder.encode(mongoDocument.getFilename(), StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedFilename + "\"";
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition);
        response.addHeader(HttpHeaders.CONTENT_TYPE, mongoDocument.getContentType());
        response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(mongoFile.length()));

        return mongoFile.getData();
    }
}
