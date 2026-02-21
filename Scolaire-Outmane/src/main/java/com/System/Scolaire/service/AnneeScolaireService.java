package com.System.Scolaire.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.System.Scolaire.model.entity.AnneeScolaire;
import com.System.Scolaire.repository.AnneeScolaireRepo;

public class AnneeScolaireService implements AnneeScolaireInter {

    @Autowired
    private AnneeScolaireRepo anneeScolaireRepo;

    public Optional<AnneeScolaire> getAnneeScolaireByAnneeID(Integer anneeID) {
        return anneeScolaireRepo.findById(anneeID);
    }
}
