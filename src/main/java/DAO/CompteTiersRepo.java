/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.comptesTiers;
import java.util.List;
import org.springframework.data.repository.Repository;

/**
 *
 * @author Rasamoelison
 */
public interface CompteTiersRepo extends Repository<comptesTiers, Long>{
    List<comptesTiers> findByIntitule(String intitule);
}
