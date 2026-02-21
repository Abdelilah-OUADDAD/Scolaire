package com.System.Scolaire.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.System.Scolaire.model.Dto.StudentDto;
import com.System.Scolaire.model.entity.Student;
import com.System.Scolaire.repository.AnneeScolaireRepo;
import com.System.Scolaire.repository.CourRepo;
import com.System.Scolaire.repository.DepartmentRepo;
import com.System.Scolaire.repository.GroupeRepo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentComponent {
    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private AnneeScolaireRepo anneeRepo;

    @Autowired
    private CourRepo courRepo;

    @Autowired
    private GroupeRepo groupeRepo;

    // Convert DTO -> Entity
    public Student toEntity(StudentDto dto) {
        Student student = new Student();
        student.setStudentID(dto.getStudentId());
        student.setNom(dto.getNom());
        student.setPrenom(dto.getPrenom());
        student.setAddress(dto.getAddress());

        // Set relations
        if (dto.getDepartmentId() != null) {
            student.setDepartment(departmentRepo.findById(dto.getDepartmentId()).orElse(null));
        }
        if (dto.getCourId() != null) {
            student.setCour((courRepo.findById(dto.getCourId()).orElse(null)));
        }
        if (dto.getAnneeId() != null) {
            student.setAnneeScolaire(anneeRepo.findById(dto.getAnneeId()).orElse(null));
        }
        if (dto.getGroupId() != null) {
            student.setGroupe(groupeRepo.findById(dto.getGroupId()).orElse(null));
        }

        return student;
    }

    // Convert Entity -> DTO
    public StudentDto toDto(Student s) {
        return StudentDto.builder()
                .studentId(s.getStudentID())
                .nom(s.getNom())
                .prenom(s.getPrenom())
                .address(s.getAddress())
                .departmentId(s.getDepartment() != null ? s.getDepartment().getDepartmentId() : null)
                .courId(s.getCour() != null ? s.getCour().getCourId() : null)
                .anneeId(s.getAnneeScolaire() != null ? s.getAnneeScolaire().getAnneeId() : null)
                .groupId(s.getGroupe() != null ? s.getGroupe().getGroupId() : null)
                .departmentName(s.getDepartment() != null ? s.getDepartment().getNomDepartment() : null)
                .anneeNiveau(s.getAnneeScolaire() != null ? s.getAnneeScolaire().getNiveauAnnee() : null)
                .groupeNom(s.getGroupe() != null ? s.getGroupe().getNomGroupe() : null)
                .NomCours(s.getCour() != null ? s.getCour().getNomCour() : null)
                .build();
    }
}
