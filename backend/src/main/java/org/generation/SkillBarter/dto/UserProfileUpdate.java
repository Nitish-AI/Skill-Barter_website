package org.generation.SkillBarter.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UserProfileUpdate {
    private String firstName;
    private String lastName;
    private String userName;
    private String bio;
    private String address;
    private String imageUrl;
    private String currentPassword;
}
