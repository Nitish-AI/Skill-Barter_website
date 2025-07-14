package org.generation.SkillBarter.repositories;

import org.generation.SkillBarter.model.ScheduledSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduledSessionRepository extends JpaRepository<ScheduledSession, Long> {
    Optional<ScheduledSession> findBySessionRequestId(Long sessionRequestId);
}
