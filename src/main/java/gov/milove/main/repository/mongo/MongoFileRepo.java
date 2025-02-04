package gov.milove.main.repository.mongo;

import gov.milove.forum.domain.MongoFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoFileRepo extends MongoRepository<MongoFile, String> {
}
