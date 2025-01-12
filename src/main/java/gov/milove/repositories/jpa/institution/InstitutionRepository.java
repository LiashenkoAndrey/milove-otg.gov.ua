package gov.milove.repositories.jpa.institution;

import gov.milove.domain.dto.InstitutionDto;
import gov.milove.domain.institution.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    @Query("select new gov.milove.domain.dto.InstitutionDto(i.id, i.title, i.iconUrl) from Institution i")
    List<InstitutionDto> getAllAsDto();

}
