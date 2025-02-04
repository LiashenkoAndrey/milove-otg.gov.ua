package gov.milove.main.repository.mongo;

import gov.milove.main.domain.AdminMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminMetadataRepo extends MongoRepository<AdminMetadata, String> {
}
