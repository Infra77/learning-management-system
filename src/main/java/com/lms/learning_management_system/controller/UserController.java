package com.lms.learning_management_system.controller;

import com.lms.learning_management_system.entity.User;
import com.lms.learning_management_system.entity.Enrollment;
import com.lms.learning_management_system.entity.Role;
import com.lms.learning_management_system.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(
            @PathVariable Long id
    ) {
        return userService.getUserById(id);
    }

    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(
            @PathVariable Role role
    ) {
        return userService.getUsersByRole(role);
    }

    @PostMapping
    public User createUser(
            @RequestBody User user
    ) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody User userDetails
    ) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(
            @PathVariable Long id
    ) {
        userService.deleteUser(id);
    }

    @GetMapping("/{userId}/enrollments")
    public List<Enrollment> getEnrollmentsByUser(
            @PathVariable Long userId
    ) {
        return userService.getEnrollmentsByUser(userId);
    }
}