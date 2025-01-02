package gov.milove.repositories.mongo;

import gov.milove.domain.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepo extends MongoRepository<Image, String> {
}
