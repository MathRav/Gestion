package DAO;
import Model.mvtotal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface mvttotalsoldeDao extends JpaRepository<mvtotal, Long>{
  @Query(value = "SELECT u,SUM(u.debit) as debit,SUM(u.credit) as credit,SUM(u.debit)-SUM(u.credit) as solde FROM mvtotal u where MONTH(u.dateMvt)>=?1 AND YEAR(u.dateMvt)>=?2 AND MONTH(u.dateMvt)<=?3 AND YEAR(u.dateMvt)<=?4 AND u.idCompte=?5 ORDER BY u.idCompte")
          //SELECT id,codeJournal,intituleJournal,idcompte,intitulePlanComptable,numerotiers,intitulePlantiers,date_Mvt,reference,libelle,echeance,SUM(debit) as debit,SUM(credit) as credit,SUM(debit)-SUM(credit) as solde FROM mvtotal WHERE date_Mvt>='2019-02-13' AND date_Mvt<='2019-02-13' GROUP BY idcompte;
//SELECT u.id,u.codeJournal,u.intituleJournal,u.idcompte,u.intitulePlanComptable,u.numerotiers,u.intitulePlantiers,u.date_Mvt,u.reference,u.libelle,u.echeance,SUM(u.debit) as debit,SUM(u.credit) as credit,SUM(u.debit)-SUM(u.credit) as solde FROM mvtotal where MONTH(u.date_Mvt)>=?1 AND YEAR(u.date_Mvt)>=?2 AND MONTH(u.date_Mvt)<=?3 AND YEAR(u.date_Mvt)<=?4 AND u.idcompte=?5 ORDER BY u.idCompte

  List<mvtotal> findAllSolde(int mois1,int annee1,int mois2,int annee2,Long idcompte);
}
