package gov.milove.main.repository.jpa;

import gov.milove.main.domain.ContactEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactEmployeeRepository extends JpaRepository<ContactEmployee, Long> {
}
