package org.generation.SkillBarter.dto;

import lombok.Data;
import org.generation.SkillBarter.enums.SessionFormat;

import java.time.LocalDateTime;

@Data
public class ScheduleSessionDTO {
    private Long sessionRequestId; // Link to accepted request
    private LocalDateTime proposedDateTime;
    private SessionFormat sessionFormat;
    private String locationOrLink;
    private String additionalNotes;
}
