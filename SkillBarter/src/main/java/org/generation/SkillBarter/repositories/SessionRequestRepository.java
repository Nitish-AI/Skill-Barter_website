package org.generation.SkillBarter.repositories;

import org.generation.SkillBarter.enums.RequestStatus;
import org.generation.SkillBarter.model.SessionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRequestRepository extends JpaRepository<SessionRequest, Long> {

    List<SessionRequest> findByRequesterId(Long userId);

    List<SessionRequest> findByReceiverId(Long userId);
    boolean existsByRequesterIdAndSkillIdAndStatus(Long requesterId, Long skillId, RequestStatus status);

}

