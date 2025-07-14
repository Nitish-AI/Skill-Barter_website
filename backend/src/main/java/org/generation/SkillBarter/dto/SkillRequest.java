package org.generation.SkillBarter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.generation.SkillBarter.enums.Category;
import org.generation.SkillBarter.enums.SessionFormat;
import org.generation.SkillBarter.enums.SkillLevel;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class SkillRequest {
    private Long id;
    @NotBlank(message="Title is required")
    @Size(min=3,max=100,message = "Title must be3-100 character")
    private String title;
    @NotBlank(message="Description is required")
    private String description;
    private Category category;
    private List<String> tags;
    private String intent;
    private SkillLevel skillLevel;
    private String prerequisites;
    private String equipment;
    private SessionFormat sessionFormat;
    private LocalDate postedDate;
    private String postedBy;

}
