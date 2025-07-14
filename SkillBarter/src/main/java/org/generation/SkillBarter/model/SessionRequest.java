package org.generation.SkillBarter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.generation.SkillBarter.enums.RequestStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="session_request", uniqueConstraints = @UniqueConstraint(columnNames = {"requester_id", "skill_id", "status"}))
public class SessionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User requester;

    @ManyToOne
    private User receiver;

    @ManyToOne
    private Skill skill;

    private String message;

    private LocalDateTime requestedAt;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private String responseMessage;
}
