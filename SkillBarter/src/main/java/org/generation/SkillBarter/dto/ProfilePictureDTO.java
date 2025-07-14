package org.generation.SkillBarter.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProfilePictureDTO {
    private MultipartFile image;
}
