package com.example.demo.service;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService  {
    private  final  EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){
        return  employeeRepository.save(employee);
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Employee getById(long id){
        return employeeRepository.findById(id).get();
    }
    public  List<Employee> findByName(String name){
        return  employeeRepository.findByName(name);
    }
    public  void deleteById(long id){
        Employee employee = employeeRepository.getOne(id);
        employeeRepository.delete(employee);
    }
}
