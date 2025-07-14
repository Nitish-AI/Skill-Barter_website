package org.generation.SkillBarter.services;

import org.generation.SkillBarter.dto.SessionRequestDTO;
import org.generation.SkillBarter.dto.SessionResponseDTO;
import org.generation.SkillBarter.enums.RequestStatus;
import org.generation.SkillBarter.model.ScheduledSession;
import org.generation.SkillBarter.model.SessionRequest;
import org.generation.SkillBarter.model.Skill;
import org.generation.SkillBarter.model.User;
import org.generation.SkillBarter.repositories.ScheduledSessionRepository;
import org.generation.SkillBarter.repositories.SessionRequestRepository;
import org.generation.SkillBarter.repositories.SkillRepository;
import org.generation.SkillBarter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionRequestService {

    @Autowired
    private SessionRequestRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ScheduledSessionRepository scheduledSessionRepository;

    public SessionRequest createRequest(SessionRequestDTO dto) {
        // Step 1: Check for existing pending request
        boolean exists = repository.existsByRequesterIdAndSkillIdAndStatus(
                dto.getRequesterId(),
                dto.getSkillId(),
                RequestStatus.PENDING
        );

        if (exists) {
            throw new RuntimeException("A request for this skill is already pending.");
        }

        // Step 2: Fetch users and skill
        User requester = userRepository.findById(dto.getRequesterId())
                .orElseThrow(() -> new RuntimeException("Requester not found"));
        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));
        Skill skill = skillRepository.findById(dto.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        // Step 3: Create new session request
        SessionRequest request = new SessionRequest();
        request.setRequester(requester);
        request.setReceiver(receiver);
        request.setSkill(skill);
        request.setMessage(dto.getMessage());
        request.setRequestedAt(LocalDateTime.now());
        request.setStatus(RequestStatus.PENDING);

        // Step 4: Save the request
        SessionRequest savedRequest = repository.save(request);

        // Step 5: Create notification for receiver
        notificationService.createNotification(
                receiver,
                requester.getUserName() + " has requested your skill: " + skill.getTitle()
        );

        return savedRequest;
    }



    public List<SessionRequest> getMyRequests(Long userId) {
        return repository.findByRequesterId(userId);
    }

    public List<SessionRequest> getIncomingRequests(Long userId) {
        return repository.findByReceiverId(userId);
    }

    public SessionRequest respondToRequest(Long requestId, SessionResponseDTO response) {
        SessionRequest request = repository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Session request not found"));
        request.setStatus(response.getStatus());
        request.setResponseMessage(response.getResponseMessage());
        return repository.save(request);
    }

    public SessionRequest acceptRequest(Long requestId) {
        SessionRequest request = repository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(RequestStatus.ACCEPTED);
        repository.save(request);

        //  Send notification to requester
        notificationService.createNotification(
                request.getRequester(),
                "Your session request has been accepted by " + request.getReceiver().getUserName()
        );

        //  Auto-create a scheduled session placeholder
        ScheduledSession session = new ScheduledSession();
        session.setSessionRequest(request);
        session.setConfirmed(false); // initially not confirmed
        scheduledSessionRepository.save(session);

        return request;
    }



    public SessionRequest rejectRequest(Long requestId) {
        SessionRequest request = repository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(RequestStatus.REJECTED);
        repository.save(request);
        // Notify requester (User 1)
        notificationService.createNotification(
                request.getRequester(),
                "Your session request for '" + request.getSkill().getTitle() + "' was rejected by " + request.getReceiver().getUserName()
        );
        return request;
    }

}

