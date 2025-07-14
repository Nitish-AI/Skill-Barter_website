package org.generation.SkillBarter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Setter
@Getter
@Entity
@Table(name = "register_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(name = "user_name",unique = true, nullable = false)
    private String userName;
    private String lastName;    
    @Column(name = "email",unique = true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(columnDefinition = "TEXT")
    private String bio;
    private String address;
    @Column(name = "date_of_join", nullable = false)
    private LocalDate dateOfJoin;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "image_public_id")
    private String imagePublicId;

    @PrePersist     //ensures it is always set correctly at the time of saving
    protected void onCreate(){
        this.dateOfJoin=LocalDate.now();
    }

    public User() {
    }

    public User(String userName,String firstName, String lastName,String email, String password, String bio, String address, String imageUrl) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName= lastName;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.address = address;
        this.imageUrl = imageUrl;
    }
}
