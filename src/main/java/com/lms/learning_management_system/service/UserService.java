package com.lms.learning_management_system.service;

import com.lms.learning_management_system.entity.User;
import com.lms.learning_management_system.entity.Enrollment;
import com.lms.learning_management_system.entity.Role;
import com.lms.learning_management_system.repository.UserRepository;
import com.lms.learning_management_system.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public UserService(UserRepository userRepository, EnrollmentRepository enrollmentRepository) {
        this.userRepository = userRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        if (userDetails.getPassword() != null) {
            user.setPassword(userDetails.getPassword());
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<Enrollment> getEnrollmentsByUser(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }

    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }
}