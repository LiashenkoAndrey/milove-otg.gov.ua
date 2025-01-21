package gov.milove.forum.repositories.mongo;

import gov.milove.forum.model.mongo.MongoMessageImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageImageRepo extends MongoRepository<MongoMessageImage, String> {
}
