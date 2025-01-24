package gov.milove.main.repository.mongo;

import gov.milove.forum.domain.MongoMessageImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageImageRepo extends MongoRepository<MongoMessageImage, String> {
}
