package com.System.Scolaire.service;

import java.util.List;

import com.System.Scolaire.model.Dto.StudentDto;

public interface StudentServiceInter {

	List<StudentDto> getAllStudents() ;
	List<StudentDto> getAllStudentsWithDetails();
	StudentDto getStudent(Integer Id);
	StudentDto getStudentWithDetails(Integer id);
	StudentDto saveStudent(StudentDto dto);
	StudentDto UpdateStudent(StudentDto dto);
	void DeleteStudent(Integer ID);
}
