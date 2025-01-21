package gov.milove.forum.repositories.jpa;

import gov.milove.forum.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepo extends JpaRepository<File, Long> {
    Optional<File> findFirstByHashCode(String hashCode);
}
