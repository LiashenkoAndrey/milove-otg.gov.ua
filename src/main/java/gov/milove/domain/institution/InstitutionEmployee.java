package gov.milove.domain.institution;

import gov.milove.domain.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "institution_employee")
public class InstitutionEmployee extends Employee {

    private Long institution_id;

    private String sub_institution;
}
