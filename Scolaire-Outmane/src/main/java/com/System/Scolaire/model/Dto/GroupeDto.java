package com.System.Scolaire.model.Dto;

import com.System.Scolaire.model.entity.AnneeScolaire;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupeDto {

    private Integer GroupId;
    private String NomGroupe;
    private Integer MaxStudent;
    private Integer CurrentStudent;
    private AnneeScolaire AnneeID;

}
