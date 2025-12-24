package com.lms.learning_management_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@Setter
@Getter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false
    )
    private String name;

    @Column(
            length = 1000
    )
    private String description;

    @Column(
            nullable = false
    )
    private Double price;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    private Category category;

    @ManyToOne
    @JoinColumn(
            name = "instructor_id",
            nullable = false
    )
    private User instructor;
}