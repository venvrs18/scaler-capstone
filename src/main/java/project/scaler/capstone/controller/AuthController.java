package project.scaler.capstone.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.scaler.capstone.dto.UserDTOs.*;
import project.scaler.capstone.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "1. Authentication", description = "Endpoints for user registration, login, and password management")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) { this.userService = userService; }

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account with email and password.")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Authenticates a user and returns a mock JWT token.")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/forgot-password")
    @Operation(summary = "Forgot Password", description = "Generates a password reset token for the given email.")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return ResponseEntity.ok("Password reset link sent (Token: " + userService.generatePasswordResetToken(request) + ")");
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Reset Password", description = "Resets the user's password using the token.")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        userService.resetPassword(request);
        return ResponseEntity.ok("Password reset successfully");
    }
}