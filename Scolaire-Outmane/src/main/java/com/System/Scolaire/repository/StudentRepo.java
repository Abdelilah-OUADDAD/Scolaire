package com.System.Scolaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.System.Scolaire.model.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    // ✅ Find by Groupe
    List<Student> findByGroupe_GroupId(Integer groupId);

    // ✅ Find by Annee
    List<Student> findByAnneeScolaire_AnneeId(Integer anneeId);

    // ✅ Find by Department
    List<Student> findByDepartment_DepartmentId(Integer departmentId);

    // ✅ Native query - Students with all info
    @Query(value = """
            SELECT s.*, d.nomDepartment, a.niveauAnnee, g.NomGroupe
            FROM Student s
            LEFT JOIN Department d ON s.DepartmentID = d.DepartmentID
            LEFT JOIN AnneeScolaire a ON s.AnneeID = a.AnneeID
            LEFT JOIN Groupe g ON s.GroupID = g.GroupID
            WHERE s.GroupID = :groupId
            """, nativeQuery = true)
    List<Object[]> findStudentsWithDetails(@Param("groupId") Integer groupId);
}
