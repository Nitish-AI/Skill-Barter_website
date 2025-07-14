package org.generation.SkillBarter.controller;

import org.generation.SkillBarter.dto.ScheduleSessionDTO;
import org.generation.SkillBarter.model.ScheduledSession;
import org.generation.SkillBarter.services.ScheduledSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skill-barter")
@CrossOrigin(origins = "http://localhost:4200")
public class ScheduledSessionController {

    @Autowired
    private ScheduledSessionService service;

    //  Schedule a new session (only if request is accepted)
    @PostMapping("/session/schedule")
    public ResponseEntity<?> scheduleSession(@RequestBody ScheduleSessionDTO dto) {
        try {
            ScheduledSession session = service.schedule(dto);
            return ResponseEntity.ok(session);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //  Get session details by request ID
    @GetMapping("/session/{requestId}")
    public ResponseEntity<?> getScheduledSession(@PathVariable Long requestId) {
        return service.getScheduleByRequestId(requestId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  Confirm session
    @PutMapping("/session/confirm/{sessionId}")
    public ResponseEntity<?> confirmSession(@PathVariable Long sessionId) {
        try {
            ScheduledSession session = service.confirmSession(sessionId);
            return ResponseEntity.ok(session);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //  Reject session
    @PutMapping("/session/reject/{sessionId}")
    public ResponseEntity<?> rejectSession(@PathVariable Long sessionId) {
        try {
            ScheduledSession session = service.rejectSession(sessionId);
            return ResponseEntity.ok(session);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //  Reschedule session
    @PutMapping("/session/reschedule/{sessionId}")
    public ResponseEntity<?> rescheduleSession(@PathVariable Long sessionId,
                                               @RequestBody ScheduleSessionDTO dto) {
        try {
            ScheduledSession session = service.rescheduleSession(sessionId, dto);
            return ResponseEntity.ok(session);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //  Mark session as completed
    @PutMapping("/session/complete/{sessionId}")
    public ResponseEntity<?> completeSession(@PathVariable Long sessionId) {
        try {
            ScheduledSession session = service.markSessionAsCompleted(sessionId);
            return ResponseEntity.ok(session);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
