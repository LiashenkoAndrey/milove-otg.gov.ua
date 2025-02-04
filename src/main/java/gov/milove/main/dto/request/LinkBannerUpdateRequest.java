package gov.milove.main.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LinkBannerUpdateRequest(
    Long id,
    String url,
    String text,
    LocalDateTime lastUpdated,
    LocalDate createdOn
) {
}
