package org.generation.SkillBarter.dto;

import lombok.Getter;
import lombok.Setter;
import org.generation.SkillBarter.enums.RequestStatus;

@Getter
@Setter
public class SessionResponseDTO {
    private RequestStatus status;
    private String responseMessage;
}
