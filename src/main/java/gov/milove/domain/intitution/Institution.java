package gov.milove.domain.intitution;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany
    private List<Employee> employee_list;
}
