package com.lms.learning_management_system.repository;

import com.lms.learning_management_system.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByUserId(Long userId);
    List<Enrollment> findByCourseId(Long courseId);
    Boolean existsByUserIdAndCourseId(Long userId, Long courseId);
}