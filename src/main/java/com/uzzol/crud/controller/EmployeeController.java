package com.uzzol.crud.controller;

import com.uzzol.crud.exeption.ResourceNotFoundExeption;
import com.uzzol.crud.model.Employee;
import com.uzzol.crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping("/employee")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") long id) throws ResourceNotFoundExeption {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("Employee not found"));
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") long employeeId, @RequestBody Employee employeeDetails) throws ResourceNotFoundExeption {
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundExeption("employee does not exist"));
        existingEmployee.setFirstName(employeeDetails.getFirstName());
        existingEmployee.setLastName(employeeDetails.getLastName());
        existingEmployee.setEmailId(employeeDetails.getEmailId());
        employeeRepository.save(existingEmployee);

        return ResponseEntity.ok().body(existingEmployee);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity destroyEmployee(@PathVariable(value = "id") long id) throws  ResourceNotFoundExeption{
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("employee does not exist"));
        return ResponseEntity.ok().build();
    }

}
