package com.guvi.springjfsjunitdemo.service;

import com.guvi.springjfsjunitdemo.entity.Employee;
import com.guvi.springjfsjunitdemo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    //injecting mocking clone dummy
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private  EmployeeService employeeService;


    //setup before each
    @BeforeEach
    public  void setup(){
        MockitoAnnotations.openMocks(this);
    }


    //develop test-1
    @Test
    public void testGetAllEmployees(){

        //create list of empty
        List<Employee> employees=new ArrayList<>();
        employees.add(new Employee(1L,"Lavish","Development"));
        employees.add(new Employee(2L,"Swapnil","Testing"));

        when(employeeRepository.findAll()).thenReturn(employees);

        //Test
        List<Employee> result = employeeService.getAllEmployees();//2
        assertEquals(2,result.size());

    }


    //develop-2 testcase for second service

    @Test
    public  void testSaveEmployee(){
        //create an object Employee
        Employee employee=new Employee(1L,"Magesh","IT");//object

        when(employeeRepository.save(employee)).thenReturn(employee);

        //service
        Employee savedEmployee = employeeService.saveEmployee(employee);
        assertEquals("Magesh",savedEmployee.getName());
        assertEquals("IT",savedEmployee.getDepartment());
    }


}
