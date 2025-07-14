package org.generation.SkillBarter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.generation.SkillBarter.enums.Category;
import org.generation.SkillBarter.enums.SessionFormat;
import org.generation.SkillBarter.enums.SkillLevel;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title",nullable = false,unique = true)
    private String title;
    @Column(columnDefinition = "text", nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)//BUSINESS,TECHNOLOGY,LANGUAGE,PERSONAL_GROWTH,OTHER
    private Category category;
    @Column(name = "tags")// save as comma - separated string
    private List<String> tags;
    private String intent;//(teach or learn)
    @Enumerated(EnumType.STRING)//("BEGINNER","INTERMEDIATE","EXPERT")
    @Column(nullable = false)
    private SkillLevel level;
    private String prerequisites;
    private String equipment;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionFormat sessionFormat;//ONLINE,OFFLINE,Both
    private LocalDate postedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")//relationship represent user can have many skill due to this bidirectional
    private User user;

    @PrePersist     //ensures it is always set correctly at the time of saving
    protected void onCreate(){
        this.postedDate=LocalDate.now();
    }

    public void setSetSkillLevel(SkillLevel skillLevel) {
        this.level = skillLevel;
    }

    public Skill() {
    }

    public Skill(String title, String description,
                 Category category, List<String> tags,
                 String intent, SkillLevel level,
                 String prerequisites, String equipment,
                 SessionFormat sessionFormat, User user) {

        this.title = title;
        this.description = description;
        this.category = category;
        this.tags = tags;
        this.intent = intent;
        this.level = level;
        this.prerequisites = prerequisites;
        this.equipment = equipment;
        this.sessionFormat = sessionFormat;
        this.user = user;
    }
}
