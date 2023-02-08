package com.uncodigo.blogspringapi.controller;

import com.uncodigo.blogspringapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    @GetMapping("/get-student")
    public Student getStudent() {
        return new Student(1, "Felipe", "Jofré");
    }

    @GetMapping("/response-entity")
    public ResponseEntity<?> getStudentResponseEntity() {

        Map<String, Object> response = new HashMap<>();
        Student student = new Student(1, "Felipe", "Jofré");
        response.put("student", student);
        response.put("ok", true);
        response.put("message", "Everything is ok!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Felipe", "Jofré"));
        students.add(new Student(2, "Roberto", "Romero"));
        students.add(new Student(3, "Alejandra", "Valenzuela"));
        students.add(new Student(4, "Leonel", "López"));

        Map<String, Object> response = new HashMap<>();
        response.put("students", students);
        response.put("ok", true);
        response.put("message", "List of students!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/{firstName}/{lastName}")
    public Student getStudentById(@PathVariable int id, @PathVariable String firstName, @PathVariable String lastName) {
        return new Student(id, StringUtils.capitalize(firstName), StringUtils.capitalize(lastName));
    }

    @GetMapping("/query")
    public Student studentRequestVariable(@RequestParam Integer id) {
        return new Student(id, "Felipe", "Jofré");
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    @PutMapping("/{id}/update")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        System.out.println("Id to update: " + id);
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        student.setId(id);
        return student;
    }

    @DeleteMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Integer id){
        System.out.println("Id student to delete: " + id);
        return "Student with id: " + id + " was deleted successfully!";
    }
}
