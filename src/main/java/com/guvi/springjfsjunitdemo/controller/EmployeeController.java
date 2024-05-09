package com.guvi.springjfsjunitdemo.controller;


import com.guvi.springjfsjunitdemo.entity.Employee;
import com.guvi.springjfsjunitdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    //inject service into controller

    @Autowired
    private EmployeeService employeeService;


    //handler to get all

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //handler for saving the employee data
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee saveEmployee = employeeService.saveEmployee(employee);
        return  new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
    }

}
