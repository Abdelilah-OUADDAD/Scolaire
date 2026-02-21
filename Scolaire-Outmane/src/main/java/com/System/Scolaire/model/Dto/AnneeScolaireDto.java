package com.System.Scolaire.model.Dto;

import java.util.List;

import com.System.Scolaire.model.entity.Groupe;
import com.System.Scolaire.model.entity.Student;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnneeScolaireDto {

    private Integer anneeId;
    private String niveauAnnee;
    private List<Student> students;
    private List<Groupe> groupes;

}
