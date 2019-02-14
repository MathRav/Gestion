package Controllers;

import DAO.mvtotalDao;
import DAO.mvttotalsoldeDao;
import Model.comptesTiers;
import Model.mvtotal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequestMapping("/totalsolde")
@Controller
public class Mvttotalsolde {
  @Autowired
  mvtotalDao mvtdao;

    @GetMapping("/page.html")
    public ModelAndView comptesTiers(){
        ModelAndView md=new ModelAndView("testmvtsolde");
        List<mvtotal> m = mvtdao.findAllSolde(02,2019,02,2019,new Long(1));
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + m.get(0).getClass().toString());
        md.addObject("liste",this.mvtdao.findAllSolde(02,2019,02,2019,new Long(1)))  ;
        return md;
    }

}
