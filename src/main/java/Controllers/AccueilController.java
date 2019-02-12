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


@RequestMapping("/")
@Controller
public class AccueilController {
  @Autowired
  comptesTiersDao cptdao;

    @GetMapping("/")
    public String getAccueil(){
        return "index";
    }

    @GetMapping("/comptesTiers")
    public ModelAndView comptesTiers(){
        ModelAndView md=new ModelAndView("pageCompteTiers");
        Iterable<comptesTiers> liste=this.cptdao.findAll(); //test
        Iterator itr=liste.iterator();
        System.out.println("hiaffiche compte zao ");
        while(itr.hasNext()){
          System.out.println(itr.next());
        }
        return md;
      }

}
