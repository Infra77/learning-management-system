package com.lms.learning_management_system.service;

import com.lms.learning_management_system.entity.Course;
import com.lms.learning_management_system.entity.Enrollment;
import com.lms.learning_management_system.entity.User;
import com.lms.learning_management_system.repository.CourseRepository;
import com.lms.learning_management_system.repository.EnrollmentRepository;
import com.lms.learning_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            UserRepository userRepository,
            CourseRepository courseRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public Enrollment enroll(Long userId, Long courseId) {
        if (enrollmentRepository.existsByUserIdAndCourseId(userId, courseId)) {
            throw new RuntimeException("Already enrolled in this course");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());
        enrollment.setIsCompleted(false);

        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    public Enrollment markCompleted(Long enrollmentId) {
        Enrollment enrollment = getEnrollmentById(enrollmentId);

        if (enrollment.getIsCompleted()) {
            throw new RuntimeException("Course already completed");
        }

        enrollment.setIsCompleted(true);
        enrollment.setCompletedAt(LocalDateTime.now());
        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}
