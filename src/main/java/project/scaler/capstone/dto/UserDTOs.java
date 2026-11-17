package project.scaler.capstone.dto;

public class UserDTOs {
    public record RegisterRequest(String email, String password, String firstName, String lastName) {}
    public record LoginRequest(String email, String password) {}
    public record ProfileUpdateRequest(String firstName, String lastName) {}
    public record ForgotPasswordRequest(String email) {}
    public record ResetPasswordRequest(String token, String newPassword) {}
    public record UserResponse(Long id, String email, String firstName, String lastName) {}
}