package com.empresa.sgah.domain.repository;

import com.empresa.sgah.domain.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Optional<Employee> findById(String id);

    List<Employee> findAll();
}
