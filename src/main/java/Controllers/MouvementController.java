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
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.Iterable;
import java.util.Iterator;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.View;
import DAO.EntrepriseDAO;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
    EntrepriseDAO epd;
    @Autowired
    planComptableDao plcdao;

    @Autowired
    PlanComptaRepo plancomptarepo;
    static ArrayList<Mouvement> mouvements ;

    @GetMapping("/page.html/{id}")
    public ModelAndView planComptablePage(@PathVariable(value="id")String id, @RequestParam(value="message",required=false) String message){
        ModelAndView md=new ModelAndView("pageMouvement");
        md.addObject("obj",epd.findById(Long.valueOf(id)).get());
        md.addObject("entid",id);
        Mouvement m = new Mouvement();
        try{
            m.setId_Journal(Long.valueOf(id));
            md.addObject("vao",m);
            md.addObject("mouvements",this.getMouvements());
            md.addObject("mouvementNonValides",this.mouvements);
            md.addObject("destination","ajouter");
            ArrayList<planComptable> tiers = this.getPlanComptable();
            md.addObject("comptables", tiers);
            md.addObject("Message",message);
            return md;
        }
        catch(Exception e){
            md.addObject("message",e.getMessage());
            md.setViewName("Creation");
        }
        finally{
            return md;
        }
    }
    @GetMapping("/valider.html/{id}")
    public String validerMouvements(@PathVariable(value="id")String id){
        TransactionStatus trans=tmanager.getTransaction(new DefaultTransactionDefinition());
        try{
            if(mouvements!=null&&this.testSiEquilibre(mouvements))
            {
                for(Mouvement mouv : mouvements)
                {
                    this.cptdao.save(mouv);
                }
                mouvements = null;
            }
            else{
                return "redirect:/mouvements/page.html/"+id+"?message=Solde non equilibré";
            }
            tmanager.commit(trans);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            tmanager.rollback(trans);
        }
        return "redirect:/mouvements/page.html/"+id;
    }

    public ArrayList<comptesTiers> getComptesTiers(){
      Iterable<comptesTiers> liste=this.tiersdao.findAll(); //test
      Iterator itr=liste.iterator();
      ArrayList<comptesTiers> vliste=new ArrayList<>();
      comptesTiers tp=null;
      while(itr.hasNext()){
        tp=(comptesTiers) itr.next();
        System.out.println(tp);
        vliste.add(tp);
      }
      return vliste;
    }
    public ArrayList<planComptable> getPlanComptable(){
      Iterable<planComptable> liste=this.plcdao.findAll(); //test
      Iterator itr=liste.iterator();
      ArrayList<planComptable> vliste=new ArrayList<>();
      planComptable tp=null;
      while(itr.hasNext()){
        tp=(planComptable) itr.next();
        System.out.println(tp);
        vliste.add(tp);
      }
      return vliste;
    }


    @PostMapping("/ajouter/{id}")
    public ModelAndView addComptesTiers(@ModelAttribute Mouvement cpt, @PathVariable(value="id")String id ){
        //this.cptdao.save(cpt);
        if(mouvements==null)
            mouvements = new ArrayList<Mouvement>();
        ModelAndView md=new ModelAndView("pageMouvement");
        md.addObject("obj",epd.findById(Long.valueOf(id)).get());
        Mouvement m = new Mouvement();
        md.addObject("entid",id);
        m.setId_Journal(cpt.getId_Journal());
        md.addObject("vao",m);
        md.addObject("destination","ajouter");
        ArrayList<planComptable> tiers = this.getPlanComptable();
        md.addObject("comptables", tiers);
        if(cpt.getDebit()!=0&&cpt.getCredit()!=0)
        {
            md.addObject("mouvements",this.getMouvements());
            md.addObject("mouvementNonValides",this.mouvements);
            md.addObject("Message","Pas de debit et credit en meme temps");
            return md;
        }
        if(cpt.getId_tiers()!=null&&!this.tiersdao.existsById(cpt.getId_tiers()))
        {
            md.addObject("mouvements",this.getMouvements());
            md.addObject("mouvementNonValides",this.mouvements);
            md.addObject("Message","Compte tier introuvable");
            return md;
        }
/*        if(!this.cptdao.existsById(cpt.getId_tiers()))
        {
            //message erreur
        }
        */
        //if(this.tiersdao.existsById(cpt.getId_tiers())&&this.cptdao.existsById(cpt.getId_tiers()))
        mouvements.add(cpt);
        md.addObject("mouvements",this.getMouvements());
        md.addObject("mouvementNonValides",this.mouvements);
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
    @GetMapping("/supprimer/{id}")
    public String supprPlanComptable(@RequestParam("id") Long id,@PathVariable(value="id")String ide){
      Mouvement cpt=this.cptdao.findById(id).get();
      if(cpt!=null) this.cptdao.delete(cpt);
      return "redirect:/mouvements/page.html/"+ide;
    }
    @GetMapping("/pageModif.html/{id}")
    public ModelAndView ModifplanComptable(@RequestParam("id") Long id, @PathVariable(value="id")String ide){
      Mouvement plc=this.cptdao.findById(id).get();
      if(plc==null) plc=new Mouvement();
        ModelAndView md=new ModelAndView("pageMouvement");
        md.addObject("entid",ide);
        md.addObject("obj",epd.findById(Long.valueOf(ide)).get());
        md.addObject("mouvements",getMouvements());
        md.addObject("vao",plc);
        md.addObject("destination","modifier");

        return md;
    }
}
