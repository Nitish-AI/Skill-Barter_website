package org.generation.SkillBarter.controller;

import org.generation.SkillBarter.dto.MatchFoundDTO;
import org.generation.SkillBarter.repositories.UserRepository;
import org.generation.SkillBarter.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill-barter")
@CrossOrigin(origins = "http://localhost:4200")
public class MatchController {

    @Autowired
    private MatchService matchService;
    @Autowired
    private UserRepository userRepository;

    // Call this to calculate and store matches
    @PostMapping("/matches/generate/{userId}")
    public ResponseEntity<?> generateMatches(@PathVariable Long userId) {
        matchService.findMatchesForUser(userId);
        return ResponseEntity.ok("Matches generated successfully.");
    }

    // Call this to retrieve stored matches
    @GetMapping("/matches/{userId}")
    public ResponseEntity<List<MatchFoundDTO>> getMatches(@PathVariable Long userId) {
        return ResponseEntity.ok(matchService.getMatchesForUser(userId));
    }


}

