package Controllers;

import DAO.CompteTiersRepo;
import DAO.EntrepriseDAO;
import DAO.comptesTiersDao;
import Model.comptesTiers;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.Iterable;
import java.util.Iterator;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;


@RequestMapping("/comptesTiers")
@Controller
public class CompteTiersController {
    @Autowired
    comptesTiersDao cptdao;
    @Autowired
    EntrepriseDAO entreprise;
    @Autowired
    CompteTiersRepo cptrepo;

    @GetMapping("/page.html/{id}")
    public ModelAndView comptesTiers(@PathVariable("id")String id){
        ModelAndView md=new ModelAndView("pageCompteTiers");
        md.addObject("listeTiers",cptrepo.findByidEntreprise(Long.valueOf(id)));
        comptesTiers ct=new comptesTiers();
        ct.setIdEntreprise(Long.valueOf(id));
        md.addObject("destination","ajouter");
        md.addObject("obj",entreprise.findById(Long.valueOf(id)).get());
        ct.setIdEntreprise(Long.valueOf(id));
        md.addObject("vao",ct);
        return md;
    }

    @GetMapping("/pageModif.html/{id}")
    public ModelAndView ModifcomptesTiers(@PathVariable("id") String identreprise,@RequestParam("id") Long id){
      comptesTiers cpt=this.cptdao.findById(id).get();
      if(cpt==null) cpt=new comptesTiers();
        ModelAndView md=new ModelAndView("pageCompteTiers");
        md.addObject("listeTiers",cptrepo.findByidEntreprise(Long.valueOf(id)));
        md.addObject("vao",cpt);
        md.addObject("destination","modifier");
        return md;
    }
    @ResponseBody
    @RequestMapping("/compteTiers")
    public String getById(@RequestParam("id") Long id,@RequestParam("eid") Long eid)
    {
        Gson g = new Gson();
        comptesTiers cpt=this.cptdao.findById(id).get();
        return g.toJson(cpt);
    }
    @ResponseBody
    @RequestMapping("/listecompteTiers")
    public String getByCompte(@RequestParam("compteCollectif") String comptecollectif,@RequestParam("id") Long eid)
    {
        Gson g = new Gson();
        List<comptesTiers> cpt=this.cptrepo.findByComptecollectifVaovao(comptecollectif,eid);
        return g.toJson(cpt);
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
        return "redirect:/comptesTiers/page.html";
    }
    @GetMapping("/supprimer")
      @Transactional
    public String supprComptesTiers(@RequestParam("id") Long id){
      comptesTiers cpt=this.cptdao.findById(id).get();
      if(cpt!=null) this.cptdao.delete(cpt);
      return "redirect:/comptesTiers/page.html";
    }

}
