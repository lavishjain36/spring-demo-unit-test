package com.guvi.springjfsjunitdemo.service;

import com.guvi.springjfsjunitdemo.entity.Employee;
import com.guvi.springjfsjunitdemo.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    //injecting mocking clone dummy
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private  EmployeeService employeeService;


    //setup before each
    @BeforeEach
    public  void setup(){
        System.out.println("Executing it before testcase");
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


    @Test
    public void testEmployeeById(){
        Employee employee=new Employee(1L,"Lavish Jain","Development");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        //service
        Optional<Employee> result = employeeService.getEmployeeById(1L);
        assertEquals("Lavish Jain",result.get().getName());
        assertEquals("Development",result.get().getDepartment());

    }

    @Test
    public  void testDeleteEmployee(){
        employeeService.deleteEmployee(1L);
        verify(employeeRepository,times(1)).deleteById(1L);
    }


    //test for getting all employees when no employee is available
    @Test
    public void testGetAllEmployees_WhenNoEmployeeExist(){
        //provide mock behaviour for ->empty array list
        when(employeeRepository.findAll()).thenReturn(new ArrayList<>());

        //call service
        List<Employee> result = employeeService.getAllEmployees();

        //assertion->to check if  is empty or not
        assertTrue(result.isEmpty());
    }

    @Test
    public  void testSaveEmployee_NotNull(){
        Employee employee=new Employee();
        employee.setName("Magesh");//actual
        employee.setDepartment("Testing");//actual

        //mock behaviour of repository->save method
        when(employeeRepository.save(employee)).thenReturn(employee);
        //call service
        Employee savedEmployee = employeeService.saveEmployee(employee);

        //check if emp is null or not
        assertNotNull(savedEmployee);

        assertEquals("Magesh",savedEmployee.getName());
        assertEquals("Testing",savedEmployee.getDepartment());


    }


    //test for getting an employee using id when employee is exisiting
    @Test
    public  void testGetEmployeeById_WhenEmployeeExist(){
        //create an object of employee
        Employee employee=new Employee();
        employee.setId(101L);
        employee.setName("Aline");
        employee.setDepartment("IT");

        //mock behaviour
        when(employeeRepository.findById(101L)).thenReturn(Optional.of(employee));

        //service
        Optional<Employee> result = employeeService.getEmployeeById(101L);
        assertTrue(result.isPresent());//true
        assertEquals("Aline",result.get().getName());
        assertEquals("IT",result.get().getDepartment());

    }


    //check if employee doesn't exist
    @Test
    public  void testGetEmployeeById_WhenEmployeeDoesNotExist(){
        //mock empty
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        //service
        Optional<Employee> result = employeeService.getEmployeeById(1L);

        //check false test case
        System.out.println(result.isPresent());
        assertFalse(result.isPresent());

    }


//    Test case update of an exisiting employee
    @Test
    public  void testUpdateEmployee_WhenEmployeexist(){
        //create an employee object->Given requirement->Given
        Employee existemployee=new Employee();
        existemployee.setId(1L);
        existemployee.setName("Lavish");
        existemployee.setDepartment("Development");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existemployee));//saved into db


        //When->Update the data
        String updatename="Lavish Jain";
        String updateDepartment="Business";
        Employee updateEmployee=new Employee();//object
        updateEmployee.setId(1L);
        updateEmployee.setName(updatename);
        updateEmployee.setDepartment(updateDepartment);

        //mock behaviour -saving the data
        when(employeeRepository.save(updateEmployee)).thenReturn(updateEmployee);


        //service->
        Employee result = employeeService.updateEmployee(updateEmployee);

//        Then
        assertNotNull(result);
        assertEquals(updatename,result.getName());
        assertEquals(updateDepartment,result.getDepartment());

    }


    //testing an employee by department
    @Test
    public  void testGetEmployeeByDepartment(){

        //Given
        List<Employee> employees=new ArrayList<>();
//        employees.add(new Employee(1L,"Lavish","Development"));
        employees.add(new Employee(2L,"Dhanush","IT"));
//        employees.add(new Employee(3L,"Niruj","HR"));
        employees.add(new Employee(4L,"Rama","IT"));

        when(employeeRepository.findByDepartment("IT")).thenReturn(employees);


        //When
//        service
        List<Employee> result = employeeService.getEmployeeByDepartment("IT");
        //Then-
        assertEquals(2,result.size());
        assertEquals("Dhanush",result.get(0).getName());
        assertEquals("IT",result.get(1).getDepartment());


    }

    @AfterEach
    public void teardown(){
        System.out.println("Testcases executed ");
    }
}
