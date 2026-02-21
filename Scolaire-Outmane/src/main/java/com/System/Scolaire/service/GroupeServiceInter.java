package com.System.Scolaire.service;

import java.util.List;

import com.System.Scolaire.model.Dto.StudentDto;
import com.System.Scolaire.model.entity.Groupe;

public interface GroupeServiceInter {

	void incrementCurrentStudent(Integer groupId);
	void  decrementCurrentStudent(Integer groupId);
	void ChangeGroupe(StudentDto Aucien, StudentDto news);
	List<Groupe> getGroupesByAnneeID(Integer anneeID);
}
