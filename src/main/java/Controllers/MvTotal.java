/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.comptesTiersDao;
import Model.comptesTiers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import DAO.mvTotal;

/**
 *
 * @author meyer
 */
@RequestMapping("/mvtJournalPlanComptable")
@Controller
public class MvTotal {
    @Autowired
  mvTotal mjpc;
    
    @GetMapping("/page.html")
    public ModelAndView mvtJournalPC(@RequestParam("date1") java.sql.Date date1,@RequestParam("date1") java.sql.Date date2){
        ModelAndView md=new ModelAndView("mvtotal");
       // md.addObject("listemvt",mjpc.getMvtParPlanComptable(date1, date2));      
        return md;
    }
    
}
