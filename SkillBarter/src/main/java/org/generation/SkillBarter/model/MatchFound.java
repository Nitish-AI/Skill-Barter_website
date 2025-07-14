package org.generation.SkillBarter.model;

import  jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.generation.SkillBarter.enums.MatchStatus;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "match_found") // "match" is a reserved SQL keyword, so this is good
public class MatchFound {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private User user; // The current user

        @ManyToOne
        private User matchedUser; // The matched user

        @NotNull
        private String matchedSkill; // What matchedUser can teach

        @NotNull
        private String desiredSkill; // What current user wants to learn

        private String skillLevel;

        private int matchPercentage;

        private String address;

        private String imageUrl;

        private String userName;
    }
