package DAO;
import Model.comptesTiers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import org.springframework.transaction.annotation.Transactional;

@Repository
public interface comptesTiersDao extends JpaRepository<comptesTiers, Long>{
/*  @Transactional(timeout = 10)
    @Override
    <S extends compteTiers> S save(S s);
    @Transactional(timeout = 10)
      @Override
      void delete(comptesTiers s);
      */
}
