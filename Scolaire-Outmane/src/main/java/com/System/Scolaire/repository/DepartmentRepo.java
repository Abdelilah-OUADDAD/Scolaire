package com.System.Scolaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.System.Scolaire.model.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}
