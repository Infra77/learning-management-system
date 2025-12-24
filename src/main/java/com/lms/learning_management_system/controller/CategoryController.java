package com.lms.learning_management_system.controller;

import com.lms.learning_management_system.entity.Category;
import com.lms.learning_management_system.entity.Course;
import com.lms.learning_management_system.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category createCategory(
            @RequestBody Category category
    ) {
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(
            @PathVariable Long id
    ) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public Category updateCategory(
            @PathVariable Long id,
            @RequestBody Category categoryDetails
    ) {
        return categoryService.updateCategory(id, categoryDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(
            @PathVariable Long id
    ) {
        categoryService.deleteCategory(id);
    }

    @GetMapping("/{id}/courses")
    public List<Course> getCoursesByCategory(
            @PathVariable Long id
    ) {
        return categoryService.getCoursesByCategory(id);
    }

}