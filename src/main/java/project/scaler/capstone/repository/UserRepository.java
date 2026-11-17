package project.scaler.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.scaler.capstone.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String resetToken);
    boolean existsByEmail(String email);
}