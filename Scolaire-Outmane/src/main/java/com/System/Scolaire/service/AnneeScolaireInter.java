package com.System.Scolaire.service;

import java.util.Optional;

import com.System.Scolaire.model.entity.AnneeScolaire;

public interface AnneeScolaireInter {

	public Optional<AnneeScolaire> getAnneeScolaireByAnneeID(Integer anneeID);
}
