package gov.milove.forum.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileDto {
    private String name;
    private Long size;
    private String format;
}
