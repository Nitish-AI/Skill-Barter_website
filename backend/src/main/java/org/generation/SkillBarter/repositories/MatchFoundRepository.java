package org.generation.SkillBarter.repositories;

import org.generation.SkillBarter.model.MatchFound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchFoundRepository extends JpaRepository<MatchFound, Long> {
    List<MatchFound> findByUserId(Long userId);
    void deleteByUserId(Long userId); // Optional: delete old matches before regenerating
}

