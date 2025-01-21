package gov.milove.forum.model.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRecord {

     private Post post;

     private PostLikesInfo postLikesInfo;
}
