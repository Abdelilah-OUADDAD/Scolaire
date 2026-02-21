package com.System.Scolaire.model.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Cour")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourID")
    private Integer courId;

    @Column(name = "NomCours")
    private String nomCour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DepartmentID", foreignKey = @ForeignKey(name = "FK_Department2"))
    private Cour DepartmentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AnneeID", foreignKey = @ForeignKey(name = "FK_AnneeID2"))
    private Cour AnneeID;
    // ✅ One Cour -> Many Students
    @OneToMany(mappedBy = "cour", cascade = CascadeType.ALL)
    private List<Student> students;
}
