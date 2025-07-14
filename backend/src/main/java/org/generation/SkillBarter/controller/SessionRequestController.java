package org.generation.SkillBarter.controller;

import org.generation.SkillBarter.dto.SessionRequestDTO;
import org.generation.SkillBarter.dto.SessionResponseDTO;
import org.generation.SkillBarter.model.SessionRequest;
import org.generation.SkillBarter.services.SessionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill-barter")
@CrossOrigin(origins = "http://localhost:4200")
public class SessionRequestController {

    @Autowired
    private SessionRequestService service;

// this api create a session request with custom message ex- from user 1 t user 2 for skill 5
    @PostMapping("/session-request")
    public ResponseEntity<SessionRequest> createRequest(@RequestBody SessionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createRequest(dto));
    }
//2. Get All Sent Requests (by requester) Fetches all session requests sent by user 1 (requester/learner)
    @GetMapping("/my-requests/{userId}")
    public ResponseEntity<List<SessionRequest>> getMyRequests(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getMyRequests(userId));
    }
//3. Get All Incoming Requests (for a user who posted skills)Fetches all session requests received by user 2 (receiver/teacher).
    @GetMapping("/incoming-requests/{userId}")
    public ResponseEntity<List<SessionRequest>> getIncomingRequests(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getIncomingRequests(userId));
    }
//4. Accept a Request .Accepts the session request with ID 10 (sets status to ACCEPTED).
    @PutMapping("/accept-request/{id}")
    public ResponseEntity<SessionRequest> accept(@PathVariable Long id) {
        return ResponseEntity.ok(service.acceptRequest(id));
    }
//5. Rejects the session request with ID 10 (sets status to REJECTED).
    @PutMapping("/reject-request/{id}")
    public ResponseEntity<SessionRequest> reject(@PathVariable Long id) {
        return ResponseEntity.ok(service.rejectRequest(id));
    }
//Updates the request's status and stores a custom reply from the receiver.
    @PutMapping("/respond-request/{id}")
    public ResponseEntity<SessionRequest> respond(@PathVariable Long id,
                                                  @RequestBody SessionResponseDTO dto) {
        return ResponseEntity.ok(service.respondToRequest(id, dto));
    }
}

