package com.guvi.springjfsjunitdemo.controller;


import com.guvi.springjfsjunitdemo.entity.Employee;
import com.guvi.springjfsjunitdemo.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public  ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(value->new ResponseEntity<>(value,HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Employee> deleteEmployee(@PathVariable Long id){
                    employeeService.deleteEmployee(id);
                    return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee updatedEmployee){
        //get the id
        Optional<Employee> existemployeeById = employeeService.getEmployeeById(id);
        if(!existemployeeById.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Employee existemployee = existemployeeById.get();//get id
        BeanUtils.copyProperties(updatedEmployee,existemployee,"id");
        Employee savedemployee = employeeService.updateEmployee(existemployee);
        return new ResponseEntity<>(savedemployee,HttpStatus.OK);
    }


    @GetMapping("/department/{department}")
    public ResponseEntity<List<Employee>>   getEmployeeByDepartment(@PathVariable String department){
        List<Employee> employees = employeeService.getEmployeeByDepartment(department);
        if(employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }


}
