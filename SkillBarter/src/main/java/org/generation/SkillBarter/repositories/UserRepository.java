package org.generation.SkillBarter.repositories;
import org.generation.SkillBarter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName); // Added for username uniqueness check
    Optional<User> findByUserName(String userName); // Added for username retrieval
    Optional<User> findByEmail(String email);
}
