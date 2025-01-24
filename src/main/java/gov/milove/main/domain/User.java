package gov.milove.main.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    @Id
    private String id;

    @NotBlank
    private String firstName;

    @Email
    private String email;

    public User(String id) {
        this.id = id;
    }

}
