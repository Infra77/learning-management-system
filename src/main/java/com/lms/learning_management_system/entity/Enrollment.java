package com.lms.learning_management_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "enrollments",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "course_id"})
)
@Getter
@Setter
@NoArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "course_id",
            nullable = false
    )
    private Course course;

    @Column(
            name = "is_completed"
    )
    private Boolean isCompleted = false;

    @Column(
            name = "enrolled_at"
    )
    private LocalDateTime enrolledAt;

    @Column(
            name = "completed_at"
    )
    private LocalDateTime completedAt;
}