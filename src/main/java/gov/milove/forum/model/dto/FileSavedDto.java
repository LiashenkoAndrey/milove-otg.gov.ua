package gov.milove.forum.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileSavedDto {
    private Long messageId;

    private String mongoId;

    private String name;

    private Boolean isLarge;
}
