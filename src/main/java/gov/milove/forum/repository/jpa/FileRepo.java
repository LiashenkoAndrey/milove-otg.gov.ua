package gov.milove.forum.repository.jpa;

import gov.milove.forum.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepo extends JpaRepository<File, Long> {
    Optional<File> findFirstByHashCode(String hashCode);
}
