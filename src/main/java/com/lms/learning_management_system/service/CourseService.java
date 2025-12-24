package com.lms.learning_management_system.service;

import com.lms.learning_management_system.entity.Category;
import com.lms.learning_management_system.entity.Course;
import com.lms.learning_management_system.entity.User;
import com.lms.learning_management_system.entity.Role;
import com.lms.learning_management_system.repository.CategoryRepository;
import com.lms.learning_management_system.repository.CourseRepository;
import com.lms.learning_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public CourseService(
            CourseRepository courseRepository,
            CategoryRepository categoryRepository,
            UserRepository userRepository
    ) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public Course createCourse(Long categoryId, Long instructorId, Course course) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        // Only INSTRUCTOR and ADMIN can create courses
        if (instructor.getRole() != Role.INSTRUCTOR && instructor.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only instructors and admins can create courses");
        }

        course.setCategory(category);
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public List<Course> getCoursesByInstructor(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    public Course updateCourse(Long id, Course courseDetails) {
        Course course = getCourseById(id);
        course.setName(courseDetails.getName());
        course.setDescription(courseDetails.getDescription());
        course.setPrice(courseDetails.getPrice());
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}