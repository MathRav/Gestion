package Controllers;

import DAO.mvtotalDao;
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

@RequestMapping("/bilan")
@Controller
public class MvtotalController {
  @Autowired
  mvtotalDao mvtdao;

    @GetMapping("/page.html")
    public ModelAndView comptesTiers(){
        ModelAndView md=new ModelAndView("testMvt");
        md.addObject("liste",this.mvtdao.findAllZavatra(new Integer(2),new Long(1L)))  ;
        return md;
    }

}
