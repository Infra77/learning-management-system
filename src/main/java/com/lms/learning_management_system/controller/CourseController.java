package com.lms.learning_management_system.controller;

import com.lms.learning_management_system.entity.Course;
import com.lms.learning_management_system.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public Course createCourse(
            @RequestParam Long categoryId,
            @RequestParam Long instructorId,
            @RequestBody Course course
    ) {
        return courseService.createCourse(categoryId, instructorId, course);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(
            @PathVariable Long id
    ) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/instructor/{instructorId}")
    public List<Course> getCoursesByInstructor(
            @PathVariable Long instructorId
    ) {
        return courseService.getCoursesByInstructor(instructorId);
    }

    @PutMapping("/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @RequestBody Course courseDetails
    ) {
        return courseService.updateCourse(id, courseDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(
            @PathVariable Long id
    ) {
        courseService.deleteCourse(id);
    }
}
