package DAO;

import Model.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ExerciceDAO extends JpaRepository<Exercice,Long> {
    public static final int estcloture=0;
    public static final int notcloture=1;
    @Transactional
    public List<Exercice> findByidentreprise(Long id);

}
