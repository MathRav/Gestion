package Controllers;

import DAO.comptesTiersDao;
import Model.comptesTiers;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequestMapping("/comptesTiers")
@Controller
public class CompteTiersController {
  @Autowired
  comptesTiersDao cptdao;

    @GetMapping("/page.html")
    public ModelAndView comptesTiers(){
        ModelAndView md=new ModelAndView("pageCompteTiers");
        md.addObject("listeTiers",getComptesTiers());
        md.addObject("vao",new comptesTiers());
        md.addObject("destination","ajouter");
        return md;
    }

    @GetMapping("/pageModif.html")
    public ModelAndView ModifcomptesTiers(@RequestParam("id") Long id){
      comptesTiers cpt=this.cptdao.findById(id).get();
      if(cpt==null) cpt=new comptesTiers();
        ModelAndView md=new ModelAndView("pageCompteTiers");
        md.addObject("listeTiers",getComptesTiers());
        md.addObject("vao",cpt);
        md.addObject("destination","modifier");
        return md;
    }



    public ArrayList<comptesTiers> getComptesTiers(){
      Iterable<comptesTiers> liste=this.cptdao.findAll(); //test
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

    @PostMapping("/ajouter")
      @Transactional
    public String addComptesTiers(@ModelAttribute comptesTiers cpt ){
        this.cptdao.save(cpt);
        return "redirect:page.html";
    }
    @GetMapping("/supprimer")
      @Transactional
    public String supprComptesTiers(@RequestParam("id") Long id){
      comptesTiers cpt=this.cptdao.findById(id).get();
      if(cpt!=null) this.cptdao.delete(cpt);
      return "redirect:page.html";
    }

}
