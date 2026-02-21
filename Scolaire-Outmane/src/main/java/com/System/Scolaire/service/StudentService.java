package com.System.Scolaire.service;

import com.System.Scolaire.Mapper.StudentComponent;
import com.System.Scolaire.model.Dto.StudentDto;
import com.System.Scolaire.model.entity.*;
import com.System.Scolaire.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService implements StudentServiceInter {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private StudentComponent studentComponent;

    //  Get all students
    public List<StudentDto> getAllStudents() {
        return studentRepo.findAll().stream()
                .map(studentComponent::toDto)
                .collect(Collectors.toList());
    }

    public List<StudentDto> getAllStudentsWithDetails() {
        List<Student> students = studentRepo.findAll();

        return students.stream().map(s -> {
            StudentDto dto = new StudentDto();
            dto.setStudentId(s.getStudentID());
            dto.setNom(s.getNom());
            dto.setPrenom(s.getPrenom());

            // Relations with null check
            if (s.getDepartment() != null) {
                dto.setDepartmentName(s.getDepartment().getNomDepartment());
            }
            if (s.getAnneeScolaire() != null) {
                dto.setAnneeNiveau(s.getAnneeScolaire().getNiveauAnnee());
            }
            if (s.getGroupe() != null) {
                dto.setGroupeNom(s.getGroupe().getNomGroupe());
            }
            if (s.getCour() != null) {
                dto.setNomCours(s.getCour().getNomCour());
            }

            return dto;
        }).collect(Collectors.toList());
    }

    // Get by ID
    public StudentDto getStudent(Integer Id) {
        Optional<Student> student = studentRepo.findById(Id);
        if (student.isPresent()) {
            return studentComponent.toDto(student.get());

        } else
            return null;

    }

    // Get One Student with Details
    public StudentDto getStudentWithDetails(Integer id) {
        Optional<Student> studentOpt = studentRepo.findById(id);

        if (!studentOpt.isPresent()) {
            return null;
        }

        Student s = studentOpt.get();
        StudentDto dto = new StudentDto();

        // Basic Info
        dto.setStudentId(s.getStudentID());
        dto.setNom(s.getNom());
        dto.setPrenom(s.getPrenom());
        dto.setAddress(s.getAddress());

        // Relations with null check
        if (s.getDepartment() != null) {
            dto.setDepartmentId(s.getDepartment().getDepartmentId());
            dto.setDepartmentName(s.getDepartment().getNomDepartment());
        }
        if (s.getAnneeScolaire() != null) {
            dto.setAnneeId(s.getAnneeScolaire().getAnneeId());
            dto.setAnneeNiveau(s.getAnneeScolaire().getNiveauAnnee());
        }
        if (s.getGroupe() != null) {
            dto.setGroupId(s.getGroupe().getGroupId());
            dto.setGroupeNom(s.getGroupe().getNomGroupe());
        }
        if (s.getCour() != null) {
            dto.setCourId(s.getCour().getCourId());
            dto.setNomCours(s.getCour().getNomCour());
        }

        return dto;
    }

    // Save student
    public StudentDto saveStudent(StudentDto dto) {
        Student student = studentComponent.toEntity(dto);
        return studentComponent.toDto(studentRepo.save(student));
    }

    // Save student
    public StudentDto UpdateStudent(StudentDto dto) {

        Student student = studentComponent.toEntity(dto);
        return studentComponent.toDto(studentRepo.save(student));
    }

    // Save student
    public void DeleteStudent(Integer ID) {
        studentRepo.deleteById(ID);
    }

}