package com.lms.learning_management_system.service;

import com.lms.learning_management_system.entity.Category;
import com.lms.learning_management_system.entity.Course;
import com.lms.learning_management_system.repository.CategoryRepository;
import com.lms.learning_management_system.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public CategoryService(
            CategoryRepository categoryRepository,
            CourseRepository courseRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = getCategoryById(id);
        category.setTitle(categoryDetails.getTitle());
        category.setDescription(categoryDetails.getDescription());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<Course> getCoursesByCategory(Long categoryId) {
        return courseRepository.findByCategoryId(categoryId);
    }
}
