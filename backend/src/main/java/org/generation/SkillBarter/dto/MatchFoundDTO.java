package org.generation.SkillBarter.dto;

import lombok.Data;
import org.generation.SkillBarter.enums.SkillLevel;
@Data
public class MatchFoundDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String matchedSkill;
    private String desiredSkill;
    private SkillLevel skillLevel;
    private int matchPercentage;
    private String imageUrl;
}
