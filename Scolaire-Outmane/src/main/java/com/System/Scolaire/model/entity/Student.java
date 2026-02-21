package com.System.Scolaire.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Student")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentID")
    private Integer StudentID;

    @Column(name = "nom", length = 255)
    private String nom;

    @Column(name = "prenom", length = 255)
    private String prenom;

    @Column(name = "Address", length = 255)
    private String address;

    // ✅ Foreign Keys - ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DepartmentID", foreignKey = @ForeignKey(name = "FK_Department"))
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CourID", foreignKey = @ForeignKey(name = "FK_Cour"))
    private Cour cour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AnneeID", foreignKey = @ForeignKey(name = "FK_AnneeID"))
    private AnneeScolaire anneeScolaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GroupID", foreignKey = @ForeignKey(name = "FK_Groupe"))
    private Groupe groupe;
}
