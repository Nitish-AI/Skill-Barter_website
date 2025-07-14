package org.generation.SkillBarter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
    private Long sessionId;
    private Long reviewerId;
    private Long revieweeId;
    private int rating;
    private String comment;
}
