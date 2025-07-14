package org.generation.SkillBarter.services;

import org.generation.SkillBarter.dto.ScheduleSessionDTO;
import org.generation.SkillBarter.enums.RequestStatus;
import org.generation.SkillBarter.enums.ScheduledStatus;
import org.generation.SkillBarter.model.ScheduledSession;
import org.generation.SkillBarter.model.SessionRequest;
import org.generation.SkillBarter.repositories.ScheduledSessionRepository;
import org.generation.SkillBarter.repositories.SessionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduledSessionService {

    @Autowired
    private ScheduledSessionRepository sessionRepository;

    @Autowired
    private SessionRequestRepository requestRepository;

    @Autowired
    private NotificationService notificationService;

    public ScheduledSession schedule(ScheduleSessionDTO dto) {
        SessionRequest request = requestRepository.findById(dto.getSessionRequestId())
                .orElseThrow(() -> new RuntimeException("Session request not found"));

        if (request.getStatus() != RequestStatus.ACCEPTED) {
            throw new RuntimeException("Cannot schedule session unless request is accepted.");
        }

        ScheduledSession session = new ScheduledSession();
        session.setSessionRequest(request);
        session.setProposedDateTime(dto.getProposedDateTime());
        session.setSessionFormat(dto.getSessionFormat());
        session.setLocationOrLink(dto.getLocationOrLink());
        session.setAdditionalNotes(dto.getAdditionalNotes());
        session.setStatus(ScheduledStatus.PENDING); // Initial status

        ScheduledSession saved = sessionRepository.save(session);

        // Notify both users
        notificationService.createNotification(
                request.getRequester(),
                "Session scheduled with " + request.getReceiver().getUserName()
        );
        notificationService.createNotification(
                request.getReceiver(),
                "Session scheduled with " + request.getRequester().getUserName()
        );

        return saved;
    }

    public ScheduledSession confirmSession(Long sessionId) {
        ScheduledSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setStatus(ScheduledStatus.CONFIRMED);

        notificationService.createNotification(
                session.getSessionRequest().getRequester(),
                "Your session has been confirmed."
        );
        notificationService.createNotification(
                session.getSessionRequest().getReceiver(),
                "Session confirmed with " + session.getSessionRequest().getRequester().getUserName()
        );

        return sessionRepository.save(session);
    }

    public ScheduledSession rejectSession(Long sessionId) {
        ScheduledSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setStatus(ScheduledStatus.REJECTED);

        notificationService.createNotification(
                session.getSessionRequest().getRequester(),
                "Your session has been rejected."
        );
        notificationService.createNotification(
                session.getSessionRequest().getReceiver(),
                "Session rejected with " + session.getSessionRequest().getRequester().getUserName()
        );

        return sessionRepository.save(session);
    }

    public ScheduledSession rescheduleSession(Long sessionId, ScheduleSessionDTO dto) {
        ScheduledSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setProposedDateTime(dto.getProposedDateTime());
        session.setSessionFormat(dto.getSessionFormat());
        session.setLocationOrLink(dto.getLocationOrLink());
        session.setAdditionalNotes(dto.getAdditionalNotes());
        session.setStatus(ScheduledStatus.PENDING); // Reset to pending

        notificationService.createNotification(
                session.getSessionRequest().getRequester(),
                "Session has been rescheduled."
        );

        return sessionRepository.save(session);
    }

    public ScheduledSession markSessionAsCompleted(Long sessionId) {
        ScheduledSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setStatus(ScheduledStatus.COMPLETED);

        notificationService.createNotification(
                session.getSessionRequest().getRequester(),
                "Session marked as completed."
        );
        notificationService.createNotification(
                session.getSessionRequest().getReceiver(),
                "Session marked as completed."
        );

        return sessionRepository.save(session);
    }

    public Optional<ScheduledSession> getScheduleByRequestId(Long requestId) {
        return sessionRepository.findBySessionRequestId(requestId);
    }
}
