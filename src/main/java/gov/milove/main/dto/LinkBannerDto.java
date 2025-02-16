package gov.milove.main.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LinkBannerDto (
    Long id,
    String url,
    String text,
    LocalDateTime lastUpdated,
    LocalDate createdOn
) {

}
