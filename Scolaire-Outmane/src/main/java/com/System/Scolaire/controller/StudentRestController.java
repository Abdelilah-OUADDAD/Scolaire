package com.System.Scolaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.System.Scolaire.model.Dto.StudentDto;
import com.System.Scolaire.service.StudentService;

@RestController
@RequestMapping("/Student")
public class StudentRestController {

	
	 
    @Autowired
    private StudentService studentService;

    @GetMapping("/get_AllStudents")
    public ResponseEntity<List<StudentDto>> getAllStudents() {

        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }
    
    @GetMapping("/get_Student")
    public StudentDto getStudent(@RequestParam Integer Id) {

        return studentService.getStudent(Id);
    }

    @PostMapping("/save")
    public StudentDto Save(@RequestBody StudentDto studentDto) {
        System.out.println(studentDto);

        return studentService.saveStudent(studentDto);
    }

    @GetMapping("/Delete")
    public void Delete(@RequestParam Integer id) {
        studentService.DeleteStudent(id);
    }

    @PutMapping("/Update")
    public StudentDto Update(@RequestBody StudentDto studentDto) {
        return studentService.UpdateStudent(studentDto);
    }
}
