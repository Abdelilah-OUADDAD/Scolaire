package com.System.Scolaire.model.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    private Integer departmentId;

    @Column(name = "NomDepartment")
    private String nomDepartment;

    // ✅ One Department -> Many Students
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Student> students;
}
