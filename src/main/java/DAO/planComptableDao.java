package DAO;
import Model.planComptable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface planComptableDao extends JpaRepository<planComptable, Long>{
  List<planComptable> findByidEntreprise(Long idEntreprise);
}
