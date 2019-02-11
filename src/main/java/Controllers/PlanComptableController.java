package Controllers;

import DAO.planComptableDao;
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

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.persistence.EntityTransaction;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.PersistenceUnit;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import DAO.EntrepriseDAO;
@RequestMapping("/planComptable")
@Controller
public class PlanComptableController {
  @Autowired
  planComptableDao plcdao;
  @Autowired
  EntrepriseDAO epd;
  @Autowired
  private PlatformTransactionManager tmanager;

    @GetMapping("/page.html/{id}")
    public ModelAndView planComptablePage(@PathVariable(value="id")String id){
        ModelAndView md=new ModelAndView("pagePlansComptables");
                md.addObject("obj",epd.findById(Long.valueOf(id)).get());
                md.addObject("entid",id);
                planComptable pct=new planComptable();
                pct.setIdEntreprise(Long.valueOf(id));
        md.addObject("listeTiers",getPlanComptable(id));
        md.addObject("vao",pct);
        md.addObject("destination","ajouter");
        return md;
    }

    @GetMapping("/pageModif.html/{id}")
    public ModelAndView ModifplanComptable(@RequestParam("id") Long id,@PathVariable(value="id")String ide){
      planComptable plc=this.plcdao.findById(id).get();
      if(plc==null) plc=new planComptable();
        ModelAndView md=new ModelAndView("pagePlansComptables");
        md.addObject("obj",epd.findById(Long.valueOf(ide)).get());
        md.addObject("entid",ide);
        md.addObject("listeTiers",getPlanComptable(ide));
        md.addObject("vao",plc);
        md.addObject("destination","modifier");
        return md;
    }



    public ArrayList<planComptable> getPlanComptable(String id){
      Iterable<planComptable> liste=this.plcdao.findByidEntreprise(Long.valueOf(id)); //test
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
    public String addPlanComptable(@ModelAttribute planComptable cpt ,@PathVariable(value="id")String id){
      TransactionStatus trans=tmanager.getTransaction(new DefaultTransactionDefinition());
          try {
            this.plcdao.save(cpt);
            tmanager.commit(trans);
          } catch(Exception ex) {
            tmanager.rollback(trans);
            ex.printStackTrace();
            throw ex;
          }
        return "redirect:/planComptable/page.html/"+id;
    }
    @GetMapping("/supprimer/{id}")
    @Transactional
    public String supprPlanComptable(@RequestParam("id") Long id,@PathVariable(value="id")String ide){
      planComptable cpt=this.plcdao.findById(id).get();
      if(cpt!=null) this.plcdao.delete(cpt);
      return "redirect:/planComptable/page.html/"+ide;
    }

}
