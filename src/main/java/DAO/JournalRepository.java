package DAO;

import Model.Exercice;
import Model.Journal;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface JournalRepository extends CrudRepository<Journal,Long> {
    @Transactional
    @Override
    <S extends Journal> S save(S s);

    @Transactional
    @Override
    void delete(Journal s);

    @Transactional
    public List<Journal> findByidEntreprise(Long id);
}
