package project.scaler.capstone.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.scaler.capstone.dto.UserDTOs.*;
import project.scaler.capstone.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "2. User Management", description = "Endpoints for managing user profiles")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/{userId}/profile")
    @Operation(summary = "Get User Profile", description = "Retrieves profile details for a specific user.")
    public ResponseEntity<UserResponse> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getProfile(userId));
    }

    @PutMapping("/{userId}/profile")
    @Operation(summary = "Update User Profile", description = "Updates the first and last name of the user.")
    public ResponseEntity<UserResponse> updateProfile(@PathVariable Long userId, @RequestBody ProfileUpdateRequest request) {
        return ResponseEntity.ok(userService.updateProfile(userId, request));
    }
}