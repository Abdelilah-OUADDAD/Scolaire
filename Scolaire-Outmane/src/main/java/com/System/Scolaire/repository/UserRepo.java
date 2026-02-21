package com.System.Scolaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.System.Scolaire.model.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
