/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.comptesTiersDao;
import DAO.mvtJournalPlanComptableDao;
import Model.comptesTiers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author meyer
 */
@RequestMapping("/mvtJournalPlanComptable")
@Controller
public class MvtJournalPlanComptableController {
    @Autowired
  mvtJournalPlanComptableDao mjpc;
    
    @GetMapping("/page.html")
    public ModelAndView mvtJournalPC(){
        ModelAndView md=new ModelAndView("mvtJournalPlanComptable");
        md.addObject("listemvt",mjpc.getAllJournalPlanComptable());      
        return md;
    }
    
}
