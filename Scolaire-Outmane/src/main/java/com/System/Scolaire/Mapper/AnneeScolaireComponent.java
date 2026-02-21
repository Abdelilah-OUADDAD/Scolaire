package com.System.Scolaire.Mapper;

import com.System.Scolaire.model.Dto.AnneeScolaireDto;
import com.System.Scolaire.model.entity.AnneeScolaire;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AnneeScolaireComponent {

    private int id;

    public AnneeScolaireDto toDto(AnneeScolaire entity) {

        return AnneeScolaireDto.builder()
                .anneeId(entity.getAnneeId())
                .niveauAnnee(entity.getNiveauAnnee())
                .build();

    }

    public AnneeScolaire toEntity(AnneeScolaireDto dto) {

        AnneeScolaire anne = new AnneeScolaire();

        anne.setAnneeId(dto.getAnneeId());
        anne.setNiveauAnnee(dto.getNiveauAnnee());

        return anne;

    }

}
