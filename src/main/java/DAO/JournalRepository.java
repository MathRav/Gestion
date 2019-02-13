package DAO;

import Model.Journal;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


public interface JournalRepository extends CrudRepository<Journal,Integer> {
    @Transactional
    @Override
    <S extends Journal> S save(S s);

    @Transactional
    @Override
    void delete(Journal s);
}
