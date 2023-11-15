package com.example.demo.rest;

import com.example.demo.domain.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {
    private  final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity create(@RequestBody Employee employee){
        Employee employee1 = employeeService.save(employee);
        return ResponseEntity.ok(employee);

    }

    @PutMapping("/employees")
    public ResponseEntity update(@RequestBody Employee employee){
        Employee employee1 = employeeService.save(employee);
        return ResponseEntity.ok(employee1);

    }

    @GetMapping("/employees")
    public ResponseEntity getAll(){
        List<Employee> employee1 = employeeService.getAll();
        return ResponseEntity.ok(employee1);
    }
    @GetMapping("/employees/{name}")
    public ResponseEntity getAll(@PathVariable String name){
        List<Employee> employee1 = employeeService.findByName(name);
        return ResponseEntity.ok(employee1);
    }
    @DeleteMapping("/employees/{id}")
    public  ResponseEntity deleteById(@PathVariable long id){
        employeeService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

}
