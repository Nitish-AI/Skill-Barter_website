package org.generation.SkillBarter.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value; // Import @Value
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {


    @Bean
    public Cloudinary cloudinary() {
        Map config = ObjectUtils.asMap(
                "cloud_name", "dxz5ewcmo",      // ✅ Replace with your actual cloud name
                "api_key", "976321673649418",            // ✅ Replace with your actual API key
                "api_secret", "QZxA86eDjkg_zC3SRQZpG60AOo4"       // ✅ Replace with your actual API secret
        );
        return new Cloudinary(config);
    }
}


