package com.System.Scolaire.model.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Groupe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Groupe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GroupID")
    private Integer groupId;

    @Column(name = "NomGroupe")
    private String nomGroupe;

    @Column(name = "MaxStudent")
    private Integer maxStudent;

    @Column(name = "CurrentStudent")
    private Integer currentStudent;

    // Many Groupes -> One Annee
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AnneID")
    private AnneeScolaire anneeScolaire;

    // One Groupe -> Many Students
    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL)
    private List<Student> students;
}
