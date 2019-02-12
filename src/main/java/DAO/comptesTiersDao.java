package DAO;
import Model.comptesTiers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface comptesTiersDao extends JpaRepository<comptesTiers, Long>{

}
