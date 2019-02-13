package DAO;

import Model.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface DeviseDAO extends JpaRepository<Devise,Long> {
    @Transactional
    public List<Devise> findByidentreprise(Long identreprise);
}
