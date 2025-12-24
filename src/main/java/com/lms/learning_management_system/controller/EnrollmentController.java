package com.lms.learning_management_system.controller;

import com.lms.learning_management_system.entity.Enrollment;
import com.lms.learning_management_system.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public Enrollment enroll(
            @RequestParam Long userId,
            @RequestParam Long courseId
    ) {
        return enrollmentService.enroll(userId, courseId);
    }

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public Enrollment getEnrollmentById(
            @PathVariable Long id
    ) {
        return enrollmentService.getEnrollmentById(id);
    }

    @PatchMapping("/{enrollmentId}/complete")
    public Enrollment markCompleted(
            @PathVariable Long enrollmentId
    ) {
        return enrollmentService.markCompleted(enrollmentId);
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(
            @PathVariable Long id
    ) {
        enrollmentService.deleteEnrollment(id);
    }
}
