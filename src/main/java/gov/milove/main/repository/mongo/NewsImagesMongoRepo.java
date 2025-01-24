package gov.milove.main.repository.mongo;

import gov.milove.main.domain.MongoNewsImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsImagesMongoRepo extends MongoRepository<MongoNewsImage, String> {
}
