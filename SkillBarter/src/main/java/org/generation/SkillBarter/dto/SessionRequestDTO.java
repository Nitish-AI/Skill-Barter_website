package org.generation.SkillBarter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionRequestDTO {
    private Long requesterId;
    private Long receiverId;
    private Long skillId;
    private String message;
}