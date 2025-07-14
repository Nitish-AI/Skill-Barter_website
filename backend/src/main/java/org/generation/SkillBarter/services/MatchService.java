package org.generation.SkillBarter.services;

import org.generation.SkillBarter.dto.MatchFoundDTO;
import org.generation.SkillBarter.enums.SkillLevel;
import org.generation.SkillBarter.model.MatchFound;
import org.generation.SkillBarter.model.Skill;
import org.generation.SkillBarter.model.User;
import org.generation.SkillBarter.repositories.MatchFoundRepository;
import org.generation.SkillBarter.repositories.SkillRepository;
import org.generation.SkillBarter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private MatchFoundRepository matchFoundRepository;

    public void findMatchesForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        matchFoundRepository.deleteByUserId(userId); // clear old matches

        List<Skill> desiredSkills = skillRepository.findByUserIdAndIntent(userId, "Learn");

        for (Skill desiredSkill : desiredSkills) {
            List<Skill> matches = skillRepository.findByIntentAndTitleContainingIgnoreCase("Teach", desiredSkill.getTitle());

            for (Skill match : matches) {
                if (!match.getUser().getId().equals(userId)) {

                    //  Compute match percentage (simple logic for now)
                    int matchPercent = calculateMatchPercentage(desiredSkill, match);

                    MatchFound mf = new MatchFound();
                    mf.setUser(user);
                    mf.setMatchedUser(match.getUser());
                    mf.setMatchedSkill(match.getTitle());
                    mf.setDesiredSkill(desiredSkill.getTitle());
                    mf.setSkillLevel(match.getLevel().toString());
                    mf.setMatchPercentage(matchPercent); //  Set it here
                    mf.setAddress(match.getUser().getAddress());
                    mf.setImageUrl(match.getUser().getImageUrl());
                    mf.setUserName(match.getUser().getUserName());

                    matchFoundRepository.save(mf);
                }
            }
        }
    }
    //helper function
    private int calculateMatchPercentage(Skill desired, Skill offered) {
        int score = 0;

        if (offered.getTitle().toLowerCase().contains(desired.getTitle().toLowerCase())) {
            score += 50;
        }

        if (offered.getTags() != null && desired.getTags() != null) {
            for (String tag : desired.getTags()) {
                if (offered.getTags().contains(tag)) {
                    score += 10;
                }
            }
        }

        return Math.min(score, 100); // Cap at 100
    }


//    public List<MatchFound> getMatches(Long userId) {
//        return matchFoundRepository.findByUserId(userId);
//    }

    public List<MatchFoundDTO> getMatchesForUser(Long userId) {
        List<MatchFound> matches = matchFoundRepository.findByUserId(userId);

        return matches.stream().map(match -> {
            MatchFoundDTO dto = new MatchFoundDTO();
            dto.setFirstName(match.getMatchedUser().getFirstName());
            dto.setLastName(match.getMatchedUser().getLastName());
            dto.setAddress(match.getMatchedUser().getAddress());
            dto.setImageUrl(match.getMatchedUser().getImageUrl());
            dto.setMatchedSkill(match.getMatchedSkill());
            dto.setDesiredSkill(match.getDesiredSkill());
            dto.setSkillLevel(SkillLevel.valueOf(match.getSkillLevel()));
            dto.setMatchPercentage(match.getMatchPercentage());
            return dto;
        }).collect(Collectors.toList());
    }

}

