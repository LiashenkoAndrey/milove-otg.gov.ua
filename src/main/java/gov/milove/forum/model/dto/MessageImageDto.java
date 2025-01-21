package gov.milove.forum.model.dto;

import gov.milove.forum.model.mongo.MongoMessageImage;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageImageDto {

    private String base64Image;

    public static MongoMessageImage toEntity(MessageImageDto messageImageDto) {
        return new MongoMessageImage(messageImageDto.getBase64Image(), messageImageDto.getBase64Image().hashCode());
    }
}
