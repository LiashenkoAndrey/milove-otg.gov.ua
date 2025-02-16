package gov.milove.main.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@MappedSuperclass
public abstract class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreationTimestamp
    protected LocalDate createdOn;

    @UpdateTimestamp
    protected LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(name = "added_by")
    protected AppUser addedBy;
}
