package DAO;
import Model.planComptable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface planComptableDao extends JpaRepository<planComptable, Long>{
  @Transactional
    @Override
    <S extends planComptable> S save(S s);
    @Transactional
      @Override
      void delete(planComptable s);
}
