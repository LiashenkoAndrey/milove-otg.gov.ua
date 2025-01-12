package gov.milove.repositories.jpa.institution;

import gov.milove.domain.institution.InstitutionEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionEmployeeRepository extends JpaRepository<InstitutionEmployee, Long> {

}
