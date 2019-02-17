package Controllers;

import Model.mvtotal;
import Model.mvtotalExt;
import DAO.mvtotalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.transaction.Transactional;
import java.util.List;
import java.util.ArrayList;


@Controller
@RequestMapping("/gl")
public class GlController {
  @Autowired
  mvtotalDao mvtdao;

    @GetMapping("/page.html")
    public ModelAndView loadView(@RequestParam(value="mois",defaultValue="1") int mois, @RequestParam(value="idex",defaultValue="1") int idExercice){
        ModelAndView mv = new ModelAndView("gl");
        List<mvtotal> liste=this.mvtdao.findAllZavatra(new Integer(mois),new Long(idExercice));
        mv.addObject("listetri",getListeTri(liste));
        mv.addObject("soldeCum",new Integer(0));
        mv.addObject("totalist",totaliser(liste));
        return mv;
    }

    public double[] totaliser(List<mvtotal> liste){
      double[] retour=new double[3];
      for(mvtotal ray: liste){
          retour[0]+=ray.getDebit();
          retour[1]+=ray.getCredit();
          retour[2]+=retour[0]-retour[1];
      }
      return retour;
    }

    public ArrayList<ArrayList<mvtotalExt>> getListeTri(List<mvtotal> liste){
      ArrayList<ArrayList<mvtotalExt>> retour=new ArrayList<ArrayList<mvtotalExt>>();
      String lastcompte=null;
      String lastcomptedtl=null;
      ArrayList<mvtotalExt> encours=null;
      double soldec=0;
      for(mvtotal ray: liste){
        if(lastcompte==null || lastcompte.charAt(0)!=(ray.getCodePlanComptable()+"").charAt(0)){
          lastcompte=ray.getCodePlanComptable();
          encours=new ArrayList<mvtotalExt>();
          retour.add(encours);
          soldec=0;
        }
        mvtotalExt vao=new mvtotalExt(ray);
        soldec+=vao.mt.getDebit();
        soldec-=vao.mt.getCredit();
        vao.soldeCum=soldec;
        encours.add(vao);
      }
      return retour;
    }
}
