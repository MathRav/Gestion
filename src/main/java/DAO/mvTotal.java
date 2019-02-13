/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.comptesTiers;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author meyer
 */
public interface mvTotal extends JpaRepository<mvTotal, Long> {
    
    //SELECT id,codeJournal,intituleJournal,numerocompte,intitulePlanComptable,numerotiers,intitulePlantiers,date_Mvt,reference,libelle,echeance,SUM(debit) as debit,SUM(credit) as credit,SUM(debit)-SUM(credit) as solde FROM mvtjournalplancomptable date_Mvt<'2019-02-13' AND date_Mvt>'2019-02-13' GROUP BY numerocompte;


  //  @Query("select m from mvtotal m")
  //  List<mvTotal> getAllJournalPlanComptable();
    
   // @Query(value = "SELECT u.id,u.codeJournal,u.intituleJournal,u.idcompte,u.intitulePlanComptable,u.numerotiers,u.intitulePlantiers,u.date_Mvt,u.reference,u.libelle,u.echeance,SUM(u.debit) as debit,SUM(u.credit) as credit,SUM(u.debit)-SUM(u.credit) as solde FROM mvtotal u where u.date_Mvt>= ?1 AND u.date_Mvt<= ?2 GROUP BY u.numerocompte",nativeQuery = true)
  //  List<mvTotal> getMvtParPlanComptable(java.sql.Date date1,java.sql.Date date2);
}
