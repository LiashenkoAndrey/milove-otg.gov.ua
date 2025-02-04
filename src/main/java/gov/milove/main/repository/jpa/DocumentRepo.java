package gov.milove.main.repository.jpa;

import gov.milove.main.domain.Document;
import gov.milove.main.dto.DocumentWithGroupDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DocumentRepo extends JpaRepository<Document, Long> {

    Optional<Document> findByHashCode(Integer hashCode);

    @Query("select count(d.id) > 1 from Document d  where d.name = :name")
    boolean documentUsedMoreThenOneTime(@Param("name") String name);

    List<DocumentWithGroupDto> searchDistinctByNameContainingIgnoreCaseOrTitleContainingIgnoreCase(String title, String name);
}
