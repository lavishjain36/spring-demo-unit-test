package com.guvi.springjfsjunitdemo.service;

import com.guvi.springjfsjunitdemo.entity.Employee;
import com.guvi.springjfsjunitdemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    //to get list of all employees
    public List<Employee> getAllEmployees(){
        return  employeeRepository.findAll();
    }

    //to save the employee data
    public  Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    //getting employee by using id
    public Optional<Employee> getEmployeeById(Long id){
        return  employeeRepository.findById(id);
    }

    //service delete an emp by id
    public  void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }
}
