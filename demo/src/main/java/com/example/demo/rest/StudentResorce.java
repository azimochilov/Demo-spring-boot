package com.example.demo.rest;

import com.example.demo.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentResorce {

    @GetMapping("/students")
    public ResponseEntity GetAll() {
        Student student = new Student(1l, "Azim", "Matem");
        Student student2 = new Student(2l, "Azzza", "aacaca");

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student2);
        return ResponseEntity.ok(studentList);

    }

    @GetMapping("/students/{id}")
    public ResponseEntity GetById(@PathVariable long id){
        Student student = new Student(id,"Aziz","eng");
        return  ResponseEntity.ok(student);
    }

    @PostMapping("/students")
    public ResponseEntity Create(@RequestBody Student student){
        return ResponseEntity.ok(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity Update(@PathVariable long id,
            @RequestBody Student updateStudent){
        Student student = new Student(1l,"Aziz","eng");
        student.setId(id);
        student.setName(updateStudent.getName());
        student.setCourse(updateStudent.getCourse());
        return  ResponseEntity.ok(student);
    }
    @DeleteMapping("/students/{id}")
    public  ResponseEntity Delete(@PathVariable long id){
        return ResponseEntity.ok("Malumot ochirildi");
    }
}