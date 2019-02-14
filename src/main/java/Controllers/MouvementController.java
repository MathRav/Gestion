/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import DAO.CompteTiersRepo;
import DAO.MouvementDao;
import DAO.PlanComptaRepo;
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
import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ModelAttribute;

@RequestMapping("/mouvements")
@Controller
public class MouvementController {
    @Autowired
    MouvementDao cptdao;
    @Autowired
    comptesTiersDao tiersdao;
    @Autowired
    private PlatformTransactionManager tmanager;
    @Autowired
    CompteTiersRepo compterepo;
    
    @Autowired
    PlanComptaRepo plancomptarepo;
    static ArrayList<Mouvement> mouvements ;
    
    @GetMapping("/page.html")
    public ModelAndView planComptablePage(){
        ModelAndView md=new ModelAndView("pageMouvement");
        Mouvement m = new Mouvement();
        m.setId_Journal(new Long(1));
        md.addObject("vao",m);
        md.addObject("mouvements",this.getMouvements());
        mouvements = null;
        md.addObject("destination","ajouter");
        List<comptesTiers> lcompte = this.compterepo.findByIntitule("Compte1");
        System.out.println("taille du repo="+lcompte.size());
        return md;
    }
    @GetMapping("/valider.html")
    public String validerMouvements(){
        TransactionStatus trans=tmanager.getTransaction(new DefaultTransactionDefinition());
        try{
            if(mouvements!=null&&this.testSiEquilibre(mouvements))
            {
                for(Mouvement mouv : mouvements)
                {
                    this.cptdao.save(mouv);
                }
            }
            else{
                return "error";
            }
            tmanager.commit(trans);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            tmanager.rollback(trans);
        }
        return "redirect:page.html";
    }
    @PostMapping("/ajouter")
    public ModelAndView addComptesTiers(@ModelAttribute Mouvement cpt ){
        //this.cptdao.save(cpt);
        if(mouvements==null)
            mouvements = new ArrayList<Mouvement>();
        ModelAndView md=new ModelAndView("pageMouvement");
        Mouvement m = new Mouvement();
        m.setId_Journal(cpt.getId_Journal());
        md.addObject("vao",m);
        if(!this.tiersdao.existsById(cpt.getId_tiers()))
        {
            //message erreur
        }
        if(!this.cptdao.existsById(cpt.getId_tiers()))
        {
            //message erreur
        }
        if(this.tiersdao.existsById(cpt.getId_tiers())&&this.cptdao.existsById(cpt.getId_tiers()))
        //if(this.tiersdao.existsById(cpt.getId_tiers())&&this.cptdao.existsById(cpt.getId_tiers()))
        if(this.tiersdao.existsById(cpt.getId_tiers()))
            mouvements.add(cpt);
        md.addObject("mouvements",mouvements);
        md.addObject("destination","ajouter");
        return md;
    }
    public boolean testSiEquilibre(ArrayList<Mouvement> mouvs)
    {
        double debit = 0,credit = 0;
        for(Mouvement m: mouvs)
        {
            debit+= m.getDebit();
            credit+= m.getCredit();
        }
        if(debit!=credit)
            return false;
        return true;
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
