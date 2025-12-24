package com.lms.learning_management_system.repository;

import com.lms.learning_management_system.entity.User;
import com.lms.learning_management_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    Boolean existsByEmail(String email);
}