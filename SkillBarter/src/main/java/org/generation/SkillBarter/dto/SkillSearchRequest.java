package org.generation.SkillBarter.dto;

import lombok.Getter;
import lombok.Setter;
import org.generation.SkillBarter.enums.Category;
import org.generation.SkillBarter.enums.SkillLevel;

@Getter
@Setter
public class SkillSearchRequest {
    private String query;
    private Category category;
    private SkillLevel level;
    private String sort;
}
