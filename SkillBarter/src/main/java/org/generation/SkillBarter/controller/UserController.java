package org.generation.SkillBarter.controller;

import jakarta.validation.Valid;
import org.generation.SkillBarter.dto.UserProfile;
import org.generation.SkillBarter.dto.UserProfileUpdate;
import org.generation.SkillBarter.dto.UserRegistrationDTO;
import org.generation.SkillBarter.model.User;
import org.generation.SkillBarter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/skill-barter")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/connect")
    public String connect() {
        return "Api is Running";
    }

    @PostMapping("/user/signup")
    public ResponseEntity<?> signup(@RequestBody UserRegistrationDTO dto) {
        try {
            User newUser = userService.registerUser(dto);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/user/login")
    public ResponseEntity<?>login(@RequestBody Map<String, String> credentials){// Login Api
        try{
            String email= credentials.get("email");
            String password= credentials.get("password");
            User user =userService.login(email,password);
            return ResponseEntity.ok(user);// OK
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());//401 unauthorised
        }
    }
    @GetMapping("/user/profile/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable Long userId){//fetch user profile after login /user/profile/2
        try{
            UserProfile profile=userService.getUserProfile(userId);
            return ResponseEntity.ok(profile);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/user/updateProfile/{userId}")
    public ResponseEntity<?> updateUserProfile(@PathVariable Long userId, @RequestBody UserProfileUpdate update){
        try{
            UserProfile updated=userService.updateUserProfile(userId,update);
            return ResponseEntity.ok(updated);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());//401 unauthorised
        }
    }

//    @PutMapping("/user/{userId}/profile-picture")
//    public ResponseEntity<?> updateProfilePicture(@PathVariable Long userId,
//                                                  @RequestParam("image") MultipartFile file) {
//        User updatedUser = userService.updateProfilePicture(userId, file);
//        return ResponseEntity.ok("Profile picture updated successfully");
//    }

}
