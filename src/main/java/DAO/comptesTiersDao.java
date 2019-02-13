package DAO;
import Model.comptesTiers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface comptesTiersDao extends JpaRepository<comptesTiers, Long>{
@Transactional
    @Override
    <S extends comptesTiers> S save(S s);
    @Transactional
      @Override
      void delete(comptesTiers s);

}
