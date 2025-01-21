package gov.milove.forum.repositories.mongo;

import gov.milove.forum.model.mongo.MongoFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoFileRepo extends MongoRepository<MongoFile, String> {
}
