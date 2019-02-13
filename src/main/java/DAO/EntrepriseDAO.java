package DAO;

import Model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseDAO extends JpaRepository<Entreprise,Long> {

}
