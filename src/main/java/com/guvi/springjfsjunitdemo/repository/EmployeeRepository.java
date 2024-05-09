package com.guvi.springjfsjunitdemo.repository;

import com.guvi.springjfsjunitdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
