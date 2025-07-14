package org.generation.SkillBarter.dto;

import lombok.Data;
import org.generation.SkillBarter.enums.SessionFormat;
import org.generation.SkillBarter.enums.SkillLevel;

import java.time.LocalDate;
import java.util.List;

@Data
public class SkillResponseDTO {
    private String title;
    private String description;
    private List<String> tags;
    private SkillLevel level;
    private SessionFormat sessionFormat;
    private LocalDate postedDate;
    private String postedBy;  // username or firstName lastName

}
