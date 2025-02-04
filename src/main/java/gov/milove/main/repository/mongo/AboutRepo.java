package gov.milove.main.repository.mongo;

import gov.milove.main.domain.About;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AboutRepo extends MongoRepository<About, String> {

}
