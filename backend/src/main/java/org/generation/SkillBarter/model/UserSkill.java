//package org.generation.SkillBarter.model;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "user_skill")
//public class UserSkill {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)//primary key with auto increment
//    private Long id;
//    @ManyToOne          //many user associated with multiple skills
//    @JoinColumn(name = "user_id",nullable = false)
//    private User user;//Foreign key
//    @ManyToOne
//    @JoinColumn(name = "skill_id",nullable = false)
//    private Skill skill;//Foreign key
//    private UserType type;//('TEACH','LEARN')
//    private Long experience;
//    private String availability;
//
//    public UserSkill() {
//    }
//
//    public UserSkill(User user, Skill skill, UserType type, Long experience, String availability) {
//        this.user = user;
//        this.skill = skill;
//        this.type = type;
//        this.experience = experience;
//        this.availability = availability;
//    }
//}
