/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.comptesTiers;
import java.util.List;
import org.springframework.data.repository.Repository;

import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author Rasamoelison
 */
public interface CompteTiersRepo extends Repository<comptesTiers, Long>{
    List<comptesTiers> findByIntitule(String intitule);
    List<comptesTiers> findByidEntreprise(Long id);
    List<comptesTiers> findByComptecollectif(String tsyaiko);


    @Query(value = "SELECT u FROM comptesTiers u where compteCollectif=?1 and idEntreprise=?2")
    List<comptesTiers> findByComptecollectifVaovao(String tsyaiko, Long ide);
}
