package com.System.Scolaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.System.Scolaire.model.entity.Groupe;

public interface GroupeRepo extends JpaRepository<Groupe, Integer> {

    List<Groupe> findByAnneeScolaire_AnneeId(Integer anneeId);

    @Query("SELECT g FROM Groupe g WHERE g.anneeScolaire.anneeId = :anneeId")
    List<Groupe> findByAnneeIdCustom(@Param("anneeId") Integer anneeId);

    @Modifying
    @Query("UPDATE Groupe g SET  g.currentStudent = :targetValue  WHERE g.groupId = :newGroupId")
    int updateGroupIdByCurrentStudent(@Param("newGroupId") Integer newGroupId,
            @Param("targetValue") Integer targetValue);

}
