package org.generation.SkillBarter.repositories;

import org.generation.SkillBarter.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByReviewedUserId(Long userId);
    boolean existsBySessionId(Long sessionId);
}
