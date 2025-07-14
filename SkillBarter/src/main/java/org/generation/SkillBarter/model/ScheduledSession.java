package org.generation.SkillBarter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.generation.SkillBarter.enums.ScheduledStatus;
import org.generation.SkillBarter.enums.SessionFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="schedule_session")
public class ScheduledSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private SessionRequest sessionRequest;

    private LocalDateTime proposedDateTime;

    @Enumerated(EnumType.STRING)
    private SessionFormat sessionFormat;
    @Enumerated(EnumType.STRING)
    private ScheduledStatus status;
    private String locationOrLink;

    private String additionalNotes;

    private boolean confirmed;

    private boolean rejected;
}
