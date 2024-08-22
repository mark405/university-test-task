package com.university.university.services.impl;

import com.university.university.entities.Department;
import com.university.university.repositories.DepartmentRepository;
import com.university.university.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }
}
