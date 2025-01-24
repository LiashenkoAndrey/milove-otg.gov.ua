package gov.milove.forum.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostLikesInfo {

    private Boolean isUserLiked;

    private Long likesAmount;
}
