package project.scaler.capstone.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.scaler.capstone.dto.UserDTOs;
import project.scaler.capstone.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // TODO: Remove {userId} path variable and extract ID from SecurityContext once JWT is implemented
    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserDTOs.UserResponse> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getProfile(userId));
    }

    // TODO: Remove {userId} path variable and extract ID from SecurityContext once JWT is implemented
    @PutMapping("/{userId}/profile")
    public ResponseEntity<UserDTOs.UserResponse> updateProfile(@PathVariable Long userId, @RequestBody UserDTOs.ProfileUpdateRequest request) {
        return ResponseEntity.ok(userService.updateProfile(userId, request));
    }
}
