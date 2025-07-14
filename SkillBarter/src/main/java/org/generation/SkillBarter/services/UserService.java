package org.generation.SkillBarter.services;

import jakarta.validation.Valid;
import org.generation.SkillBarter.dto.UserProfile;
import org.generation.SkillBarter.dto.UserProfileUpdate;
import org.generation.SkillBarter.dto.UserRegistrationDTO;
import org.generation.SkillBarter.model.User;
import org.generation.SkillBarter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired private CloudinaryService cloudinaryService;

    public User registerUser(UserRegistrationDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered.");
        }

        if (userRepository.existsByUserName(dto.getUserName())) {
            throw new RuntimeException("Username already taken.");
        }

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setBio(dto.getBio());
        user.setAddress(dto.getAddress());
        user.setDateOfJoin(LocalDate.now());
        user.setImageUrl("https://res.cloudinary.com/dxz5ewcmo/image/upload/v1752417718/avatar_jrppmm.svg");

        return userRepository.save(user);
    }


    public User login(String email,String password){
        User user=userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        return user;
    }
    public UserProfile getUserProfile(Long userId){
        User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not Found"));
        return new UserProfile(
                user.getFirstName(),                //Display this data of user on profile page
                user.getLastName(),
                user.getUserName(),
                user.getBio(),
                user.getAddress(),
                user.getDateOfJoin(),
                user.getImageUrl()
        );
    }
    public UserProfile updateUserProfile(Long userId,UserProfileUpdate update){
        User user=userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not Found"));
        if(!passwordEncoder.matches(update.getCurrentPassword(), user.getPassword())){
            throw new RuntimeException("Incorrect Password");
        }
        user.setFirstName(update.getFirstName());            //this will verify the password and then user can edit the user details
        user.setLastName(update.getLastName());
        user.setUserName(update.getUserName());
        user.setBio(update.getBio());
        user.setAddress(update.getAddress());
        user.setImageUrl(update.getImageUrl());
        userRepository.save(user);

        return new UserProfile(
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getBio(),
                user.getAddress(),
                user.getDateOfJoin(),
                user.getImageUrl()
        );

    }

//    public User updateProfilePicture(Long userId, MultipartFile file) {
//        // 1. Find the user
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found."));
//
//        try {
//            // 2. Handle image update logic
//            if (file != null && !file.isEmpty()) {
//                // If a new file is provided:
//                // Delete old image from Cloudinary if it exists
//                if (user.getImagePublicId() != null && !user.getImagePublicId().isEmpty()) {
//                    // Only attempt to delete if it's not the default image public ID
//                    if (!user.getImagePublicId().equals(DEFAULT_IMAGE_PUBLIC_ID)) {
//                        cloudinaryService.delete(user.getImagePublicId());
//                    }
//                }
//

//                // Upload new image
//                // Cast the result to Map<String, Object> to resolve unchecked assignment warning
//                Map<String, Object> uploadResult = cloudinaryService.upload(file, "skillbarter_profile_images");
//
//                user.setImageUrl((String) uploadResult.get("secure_url"));
//                user.setImagePublicId((String) uploadResult.get("public_id"));
//
//            } else {
//                // If no new file is provided (file is null or empty)
//                // Option A: Revert to default image (common scenario for "remove profile picture")
//                if (user.getImagePublicId() != null && !user.getImagePublicId().isEmpty() && !user.getImagePublicId().equals(DEFAULT_IMAGE_PUBLIC_ID)) {
//                    // Delete the current custom image if it's not already the default
//                    cloudinaryService.delete(user.getImagePublicId());
//                }
//                user.setImageUrl(DEFAULT_IMAGE_URL);
//                user.setImagePublicId(DEFAULT_IMAGE_PUBLIC_ID);
//
//                // Option B: Throw an error if a file is strictly required for this update function.
//                // If you want to force an image upload, uncomment the line below and remove Option A.
//                // throw new IllegalArgumentException("A new profile picture file is required for update.");
//            }
//
//            // 3. Save the updated user entity
//            return userRepository.save(user);
//
//        } catch (IOException e) {
//            // Re-throw as a more specific custom exception for API clients
//            throw new RuntimeException(("Failed to upload or delete profile picture: "+ e.getMessage()));
//        }
//    }




}
