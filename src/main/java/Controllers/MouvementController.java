/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import DAO.MouvementDao;
import DAO.comptesTiersDao;
import DAO.planComptableDao;
import Model.Mouvement;
import Model.comptesTiers;
import Model.planComptable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.Iterable;
import java.util.Iterator;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ModelAttribute;

@RequestMapping("/mouvements")
@Controller
public class MouvementController {
    @Autowired
    MouvementDao cptdao;

    @GetMapping("/page.html")
    public ModelAndView planComptablePage(){
        ModelAndView md=new ModelAndView("pageMouvement");
        Mouvement m = new Mouvement();
        m.setId_Journal(new Long(1));
        md.addObject("vao",m);
        md.addObject("mouvements",this.getMouvements());
        md.addObject("destination","ajouter");
        return md;
    }
    @PostMapping("/ajouter")
    public String addComptesTiers(@ModelAttribute Mouvement cpt ){
        //this.cptdao.save(cpt);
        cptdao.save(cpt);
        return "redirect:page.html";
    }
    public ArrayList<Mouvement> getMouvements(){
        System.out.println("liste mouvements");
      Iterable<Mouvement> liste=this.cptdao.findAll(); //test
      Iterator itr=liste.iterator();
      ArrayList<Mouvement> vliste=new ArrayList<>();
      Mouvement tp=null;
      while(itr.hasNext()){
        tp=(Mouvement) itr.next();
        System.out.println(tp);
        vliste.add(tp);
      }
      System.out.println("taille="+this.cptdao.count());
      return vliste;
    }
    @GetMapping("/supprimer")
    public String supprPlanComptable(@RequestParam("id") Long id){
      Mouvement cpt=this.cptdao.findById(id).get();
      if(cpt!=null) this.cptdao.delete(cpt);
      return "redirect:page.html";
    }
    @GetMapping("/pageModif.html")
    public ModelAndView ModifplanComptable(@RequestParam("id") Long id){
      Mouvement plc=this.cptdao.findById(id).get();
      if(plc==null) plc=new Mouvement();
        ModelAndView md=new ModelAndView("pageMouvement");
        md.addObject("mouvements",getMouvements());
        md.addObject("vao",plc);
        md.addObject("destination","modifier");
        //this.cptdao.saveAll(itrbl);
        return md;
    }
}
