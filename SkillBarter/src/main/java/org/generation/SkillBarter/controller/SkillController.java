package org.generation.SkillBarter.controller;

import jakarta.validation.Valid;
import org.generation.SkillBarter.dto.SkillRequest;
import org.generation.SkillBarter.dto.SkillResponseDTO;
import org.generation.SkillBarter.enums.Category;
import org.generation.SkillBarter.enums.SessionFormat;
import org.generation.SkillBarter.enums.SkillLevel;
import org.generation.SkillBarter.model.MatchFound;
import org.generation.SkillBarter.model.Skill;
import org.generation.SkillBarter.services.MatchService;
import org.generation.SkillBarter.services.SkillService;
import org.generation.SkillBarter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/skill-barter")
@CrossOrigin(origins = "http://localhost:4200")
public class SkillController {
    @Autowired
    private UserService userService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private MatchService matchFoundService;

    @PostMapping("/post/{userId}")
    public ResponseEntity<Skill> postSkill(@PathVariable Long userId,//api for posting skill
                                           @RequestBody SkillRequest request){
        Skill skill =skillService.createSkill(userId,request);
        //this line to auto-generate matches when user post the skill
        matchFoundService.findMatchesForUser(userId);
        return ResponseEntity.ok(skill);
    }
    @GetMapping("/user/{userId}/skills")
    public ResponseEntity<?> getAllSkills(@PathVariable Long userId){//fetch user posted skills by user id
        return ResponseEntity.ok(skillService.getAllSkillByUser(userId));
    }
    @DeleteMapping("/user/{userId}/skill/{skillId}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long userId, @PathVariable Long skillId) {
        try {
            skillService.deleteSkill(userId, skillId);
            return ResponseEntity.ok("Skill deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/user/{userId}/skill/{skillId}")
    public ResponseEntity<?> updateSkill(@PathVariable Long userId,
                                         @PathVariable Long skillId,
                                         @Valid @RequestBody SkillRequest request,
                                         BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }

        Skill updatedSkill = skillService.updateSkill(userId, skillId, request);
        return ResponseEntity.ok(updatedSkill);
    }
    @GetMapping("/skills/{userId}")
    public ResponseEntity<?> getSkill(@PathVariable Long userId) {// get specific user skill by user id
        try {
            List<Skill> skills = skillService.getSkillByUserId(userId);
            return ResponseEntity.ok(skills);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    @GetMapping("/skills/teach/others/{userId}")
    public ResponseEntity<?> getTeachingSkillsFromOthers(@PathVariable Long userId) {
        List<SkillResponseDTO> skills = skillService.getOtherUsersTeachingSkills(userId);
        return ResponseEntity.ok(skills);
    }
    @GetMapping("/skills/search")
    public ResponseEntity<List<SkillResponseDTO>> searchSkills(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) SkillLevel level,
            @RequestParam(required = false) SessionFormat sessionFormat,
            @RequestParam(defaultValue = "newest") String sort
    ) {
        List<SkillResponseDTO> results = skillService.searchSkills(query, category, level, sessionFormat, sort);
        return ResponseEntity.ok(results);
    }


}
