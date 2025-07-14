package org.generation.SkillBarter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class UserProfile {
    private String firstName;
    private String lastName;
    private String userName;
    private String bio;
    private String address;
    private LocalDate dateOfJoin;
    private String imageUrl;
}
