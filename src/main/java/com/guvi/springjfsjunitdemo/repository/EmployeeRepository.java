package com.guvi.springjfsjunitdemo.repository;

import com.guvi.springjfsjunitdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    //method -spring datajpa will create it
    List<Employee> findByDepartment(String department);
}
