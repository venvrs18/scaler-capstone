package project.scaler.capstone.service;

import project.scaler.capstone.dto.UserDTOs.*;
import project.scaler.capstone.model.User;

import org.springframework.stereotype.Service;
import project.scaler.capstone.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already in use");
        }
        User user = new User();
        user.setEmail(request.email());
        user.setPassword(request.password()); // TODO: BCrypt encode later
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());

        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getFirstName(), savedUser.getLastName());
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // TODO: Replace with Spring AuthenticationManager later
        if (!user.getPassword().equals(request.password())) {
            throw new RuntimeException("Invalid credentials");
        }

        return "mock-jwt-token-for-user-" + user.getId(); // TODO: Generate actual JWT
    }

    public UserResponse getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName());
    }

    public UserResponse updateProfile(Long userId, ProfileUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        User updatedUser = userRepository.save(user);

        return new UserResponse(updatedUser.getId(), updatedUser.getEmail(), updatedUser.getFirstName(), updatedUser.getLastName());
    }

    public String generatePasswordResetToken(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setResetTokenExpiry(LocalDateTime.now().plusHours(1));
        userRepository.save(user);

        // TODO: Integrate AWS SES to send the email with the token link here
        return token;
    }

    public void resetPassword(ResetPasswordRequest request) {
        User user = userRepository.findByResetToken(request.token())
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

        if (user.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        user.setPassword(request.newPassword()); // TODO: BCrypt encode later
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        userRepository.save(user);
    }
}