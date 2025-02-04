package gov.milove.main.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

@Entity
@Getter
@Setter
@Table(name = "link_banner")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class LinkBanner extends Banner {

    @Size(min = 4, max = 255)
    @NotNull
    private String text;

    @URL
    @NotNull
    private String url;
}