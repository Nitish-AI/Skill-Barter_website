package org.generation.SkillBarter.dto;

import lombok.Data;

@Data
public class SignupRequestDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String bio;
    private String address;
    // No imageUrl here — image will come as MultipartFile
}
