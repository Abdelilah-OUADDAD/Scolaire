package com.System.Scolaire.model.entity;

import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AnneeScolaire")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnneeScolaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnneeID")
    private Integer anneeId;

    @Column(name = "niveauAnnee")
    private String niveauAnnee;

    // ✅ One Annee -> Many Students
    @OneToMany(mappedBy = "anneeScolaire", cascade = CascadeType.ALL)
    private List<Student> students;

    // ✅ One Annee -> Many Groupes
    @OneToMany(mappedBy = "anneeScolaire", cascade = CascadeType.ALL)
    private List<Groupe> groupes;

}
