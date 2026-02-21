package com.System.Scolaire.model.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Integer studentId;

    @NotBlank(message = "Nom obligatoire!")
    private String nom;

    @NotBlank(message = "Prénom obligatoire!")
    private String prenom;

    private String address;

    // Foreign Keys (IDs)
    private Integer departmentId;
    private Integer courId;
    private Integer anneeId;
    private Integer groupId;

    // Info mn relations
    private String departmentName;
    private String anneeNiveau;
    private String groupeNom;
    private String NomCours;

    public String getNomComplet() {
        return prenom + " " + nom;
    }
}