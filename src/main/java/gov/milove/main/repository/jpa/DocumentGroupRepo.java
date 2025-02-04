package gov.milove.main.repository.jpa;

import gov.milove.main.domain.DocumentGroup;
import gov.milove.main.dto.DocumentGroupWithGroupsDto;
import gov.milove.main.dto.DocumentGroupWithGroupsDtoAndDocumentsDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentGroupRepo extends JpaRepository<DocumentGroup, Long> {

    Optional<DocumentGroupWithGroupsDtoAndDocumentsDto> findDistinctById(Long id);

    List<DocumentGroupWithGroupsDto> findDistinctByDocumentGroupIdOrderByCreatedOn(Long id);
}
