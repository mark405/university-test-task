package com.university.university.services;

import com.university.university.entities.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Optional<Department> getDepartmentByName(String name);
}
