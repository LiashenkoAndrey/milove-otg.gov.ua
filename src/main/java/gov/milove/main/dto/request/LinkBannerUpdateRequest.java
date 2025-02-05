package gov.milove.main.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

public record LinkBannerUpdateRequest (
    @NonNull
    Long id,
    @NonNull @URL
    String url,
    @NotBlank @Size(min = 3)
    String text,
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime lastUpdated,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate createdOn
) {
}
