package com.example.graphqlDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.graphqlDemo.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

}
