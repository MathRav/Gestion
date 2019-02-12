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
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ModelAttribute;

@RequestMapping("/planComptable")
@Controller
public class PlanComptableController {
  @Autowired
  planComptableDao plcdao;

    @GetMapping("/page.html")
    public ModelAndView planComptablePage(){
        ModelAndView md=new ModelAndView("pagePlansComptables");
        md.addObject("listeTiers",getPlanComptable());
        md.addObject("vao",new planComptable());
        md.addObject("destination","ajouter");
        return md;
    }

    @GetMapping("/pageModif.html")
    public ModelAndView ModifplanComptable(@RequestParam("id") Long id){
      planComptable plc=this.plcdao.findById(id).get();
      if(plc==null) plc=new planComptable();
        ModelAndView md=new ModelAndView("pagePlansComptables");
        md.addObject("listeTiers",getPlanComptable());
        md.addObject("vao",plc);
        md.addObject("destination","modifier");
        return md;
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

    @PostMapping("/ajouter")
    public String addPlanComptable(@ModelAttribute planComptable cpt ){
        this.plcdao.save(cpt);
        return "redirect:page.html";
    }
    @GetMapping("/supprimer")
    public String supprPlanComptable(@RequestParam("id") Long id){
      planComptable cpt=this.plcdao.findById(id).get();
      if(cpt!=null) this.plcdao.delete(cpt);
      return "redirect:page.html";
    }

}
