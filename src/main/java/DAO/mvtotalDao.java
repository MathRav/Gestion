package DAO;
import Model.mvtotal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface mvtotalDao extends JpaRepository<mvtotal, Long>{
  @Query(value = "SELECT u FROM mvtotal u where MONTH(u.dateMvt)=?1 AND u.idExercice=?2 ORDER BY u.idCompte, u.dateMvt ASC")
  List<mvtotal> findAllZavatra(Integer mois,Long idex);
}
