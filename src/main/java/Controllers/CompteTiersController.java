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
    public String addComptesTiers(@ModelAttribute comptesTiers cpt ){
        this.cptdao.save(cpt);
        return "redirect: /comptesTiers/page.html";
    }

}
