package DAO;
import Model.planComptable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface planComptableDao extends JpaRepository<planComptable, Long>{
  /*@Transactional(timeout = 10)
=======
  @Transactional
>>>>>>> 3929a57fe9aa6e359390d15e7c36da7dfb0dd40a
    @Override
    <S extends planComptable> S save(S s);
    @Transactional
      @Override
      void delete(planComptable s);*/
}
