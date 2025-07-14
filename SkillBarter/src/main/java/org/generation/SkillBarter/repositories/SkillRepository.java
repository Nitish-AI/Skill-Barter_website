package org.generation.SkillBarter.repositories;

import org.generation.SkillBarter.enums.Category;
import org.generation.SkillBarter.enums.SessionFormat;
import org.generation.SkillBarter.enums.SkillLevel;
import org.generation.SkillBarter.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill,Long> {
    List<Skill> findByCategory(Category category);
    List<Skill> findByUserId(Long userId);
    List<Skill> findByUserIdAndIntent(Long userId,String Intent);
    List<Skill> findByIntent(String intent);
    Optional<Skill> findByIdAndUserId(Long skillId,Long userId);
    List<Skill> findByUserIdNotAndIntentIgnoreCase(Long userId, String intent);
    @Query("SELECT s FROM Skill s WHERE " +
            "(:query IS NULL OR LOWER(s.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "   OR LOWER(s.user.userName) LIKE LOWER(CONCAT('%', :query, '%'))) " +
            "AND (:category IS NULL OR s.category = :category) " +
            "AND (:level IS NULL OR s.level = :level) " +
            "AND (:sessionFormat IS NULL OR s.sessionFormat = :sessionFormat) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'newest' THEN s.postedDate END DESC, " +
            "CASE WHEN :sort = 'oldest' THEN s.postedDate END ASC")
    List<Skill> searchSkills(
            @Param("query") String query,
            @Param("category") Category category,
            @Param("level") SkillLevel level,
            @Param("sessionFormat") SessionFormat sessionFormat,
            @Param("sort") String sort
    );

    List<Skill> findByIntentAndTitleContainingIgnoreCase(String intent, String title);


}
