package gov.milove.main.repository.mongo;

import gov.milove.main.domain.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepo extends MongoRepository<Image, String> {
}
