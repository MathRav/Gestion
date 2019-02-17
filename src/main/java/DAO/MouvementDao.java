/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Mouvement;
import Model.planComptable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;


/**
 *
 * @author Rasamoelison
 */
@Repository
public interface MouvementDao extends JpaRepository<Mouvement, Long>{

    @Transactional
    @Override
    public <S extends Mouvement> S save(S s);
    @Query("select u from mvt u where MONTH(u.date_Mvt)=?1 and u.id_exercice=?2 and u.id_Journal=?3")
    public List<Mouvement> findMouvementMoisExercice(Integer mois, Long idExercice,Long idJournal);

}
