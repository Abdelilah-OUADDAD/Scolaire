package com.System.Scolaire.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.System.Scolaire.model.Dto.StudentDto;
import com.System.Scolaire.model.entity.Groupe;
import com.System.Scolaire.repository.GroupeRepo;

import jakarta.transaction.Transactional;

@Service

public class GroupeService implements GroupeServiceInter{
    @Autowired
    private GroupeRepo groupeRepo;

    public List<Groupe> getGroupesByAnneeID(Integer anneeID) {
        return groupeRepo.findByAnneeScolaire_AnneeId(anneeID);
    }

    @Transactional
    public void ChangeGroupe(StudentDto Aucien, StudentDto news) {

        // previous table Groupe
        Optional<Groupe> groupe = groupeRepo.findById(Aucien.getGroupId());
        Groupe g = groupe.get();
        Integer targetValue = g.getCurrentStudent() - 1;
        groupeRepo.updateGroupIdByCurrentStudent(Aucien.getGroupId(), targetValue);

        // New update Table Group
        groupe = groupeRepo.findById(news.getGroupId());
        targetValue = groupe.get().getCurrentStudent() + 1;
        groupeRepo.updateGroupIdByCurrentStudent(news.getGroupId(), targetValue);

    }

    // increment Current Student
    @Transactional
    public void incrementCurrentStudent(Integer groupId) {
        Groupe groupe = groupeRepo.findById(groupId).orElse(null);
        if (groupe != null) {
            groupe.setCurrentStudent(groupe.getCurrentStudent() + 1);
            groupeRepo.save(groupe);
        }
    }

    // decrement Current Student
    @Transactional
    public void decrementCurrentStudent(Integer groupId) {
        Groupe groupe = groupeRepo.findById(groupId).orElse(null);
        if (groupe != null && groupe.getCurrentStudent() > 0) {
            groupe.setCurrentStudent(groupe.getCurrentStudent() - 1);
            groupeRepo.save(groupe);
        }
    }
}
