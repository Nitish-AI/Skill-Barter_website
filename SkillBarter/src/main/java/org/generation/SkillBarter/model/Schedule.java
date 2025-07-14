//package org.generation.SkillBarter.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "schedule")
//public class Schedule {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @OneToOne
//    @JoinColumn(name = "match_id",nullable = false)
//    private Match match;
//    @Column(nullable = false)
//    private LocalDate sessionDate;
//    @Column(nullable = false)
//    private LocalTime sessionTime;
//    @Column(nullable = false)
//    private Integer duration;
//    private String meetingLink;
//
//    public Schedule() {
//    }
//
//    public Schedule(Match match, LocalDate sessionDate, LocalTime sessionTime, Integer duration, String meetingLink) {
//        this.match = match;
//        this.sessionDate = sessionDate;
//        this.sessionTime = sessionTime;
//        this.duration = duration;
//        this.meetingLink = meetingLink;
//    }
//}
