package Controllers;

import DAO.DeviseDAO;
import DAO.EntrepriseDAO;
import DAO.ExerciceDAO;
import Model.Devise;
import Model.Entreprise;
import Model.Exercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@RequestMapping("/Entreprise")
@Controller
public class Entreprisecontroller {
    @Autowired
    private EntrepriseDAO entreprise;
    @Autowired
    private ExerciceDAO exercice;
    @Autowired
    private DeviseDAO devise;
    @GetMapping("/Creation")
    public ModelAndView CreateEntreprise(){
        ModelAndView mda=new ModelAndView("Creation");
        mda.addObject("liste",entreprise.findAll());
        return mda;
    }
    @GetMapping("/CreationDevise/{id}")
    public ModelAndView Devises(@PathVariable("id")Long identreprise) throws Exception{
        ModelAndView liste=new ModelAndView("Devise");
        liste.addObject("liste",devise.findByidentreprise(identreprise));
        liste.addObject("obj",entreprise.findById(identreprise).get());
        return liste;
    }
    @Transactional
    @PostMapping("/CreerDevise/{id}")
    public ModelAndView CreationDevise(@PathVariable("id")Long identreprise,@RequestParam("intitule")String intitule,@RequestParam("valeur")String valeur) throws Exception{
        ModelAndView liste=new ModelAndView("Devise");
        liste.addObject("obj",entreprise.findById(identreprise).get());
        try{
            Devise d=new Devise();
            d.setIdentreprise(identreprise);
            d.setIntitule(intitule);
            d.setValeur(Double.valueOf(valeur));
            devise.save(d);
            liste.addObject("Message","Success");
        }
        catch(Exception e){
            e.printStackTrace();
            liste.addObject("Message",e.getMessage());
        }
        finally{
            liste.addObject("liste",devise.findByidentreprise(identreprise));
            return liste;
        }
    }
    @GetMapping("/CreationExercice/{id}")
    public ModelAndView Exercices(@PathVariable("id")Long identreprise) throws Exception{
        ModelAndView liste=new ModelAndView("Exercice");
        liste.addObject("liste",exercice.findByidentreprise(identreprise));
        liste.addObject("obj",entreprise.findById(identreprise).get());
        return liste;
    }
    @Transactional
    @PostMapping("/CreerExercice/{id}")
    public ModelAndView CreerExercice(@PathVariable("id")Long identreprise,@RequestParam("annee")int an) throws Exception{
        ModelAndView liste=new ModelAndView("Exercice");
        liste.addObject("obj",entreprise.findById(identreprise).get());
        try{
            Exercice e=new Exercice();
            e.setAnnee(an);e.setIdentreprise(identreprise);e.setIsclotured(ExerciceDAO.notcloture);
            exercice.save(e);
            liste.addObject("Message","Success");
        }
        catch(Exception e){
            liste.addObject("Message",e.getMessage());
        }
        finally{
            liste.addObject("liste",exercice.findByidentreprise(identreprise));
            return liste;
        }
    }
    @Transactional
    @PostMapping("/Creer")
    public ModelAndView Creation(@RequestParam("nom")String nom, @RequestParam("adresse")String adresse, @RequestParam("code")String code, @RequestParam("devise")String devise, @RequestParam("allocCodes")String allocCode){
        ModelAndView mda=new ModelAndView("Creation");
        try{
            Entreprise e=new Entreprise();
            e.setNom(nom);e.setAdresse(adresse);e.setCode(Integer.valueOf(code));e.setDevise(devise);e.setAlloccodes(Integer.valueOf(allocCode));
            entreprise.save(e);
            mda.addObject("Message","Success");
        }
        catch(Exception e){
            mda.addObject("Message",e.getMessage());
        }
        finally{
            mda.addObject("liste",entreprise.findAll());
            return mda;
        }
    }

}
