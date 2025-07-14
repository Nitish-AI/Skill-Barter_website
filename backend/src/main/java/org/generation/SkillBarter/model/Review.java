package org.generation.SkillBarter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="session_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User reviewer; // User who leaves the review

    @ManyToOne
    private User reviewedUser; // User who is being reviewed

    @OneToOne
    private ScheduledSession session; // Link to the session

    private int rating; // 1 to 5

    @Column(length = 500)
    private String comment;

    private LocalDateTime createdAt = LocalDateTime.now();
}
