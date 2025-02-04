package gov.milove.main.repository.mongo;

import gov.milove.main.domain.MongoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface MongoDocumentRepo extends MongoRepository<MongoDocument, String> {

    List<MongoDocument> findByFilename(String filename);

    void deleteByFilename(String filename);
}
