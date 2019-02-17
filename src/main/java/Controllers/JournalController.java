package Controllers;

import DAO.EntrepriseDAO;
import DAO.ExerciceDAO;
import DAO.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import Model.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Journal")
public class JournalController {
    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private ExerciceDAO exercice;
    @Autowired
    private EntrepriseDAO entreprise;

    @GetMapping("/journal.html/{id}")
    public ModelAndView loadView(@PathVariable("id")String identreprise){
        ModelAndView mv = new ModelAndView("journal");
        mv.addObject("journals",journalRepository.findByidEntreprise(Long.valueOf(identreprise)));
        Journal j = new Journal();
        j.setIdEntreprise(Long.valueOf(identreprise));
        mv.addObject("vao",j);
        mv.addObject("obj",entreprise.findById(Long.valueOf(identreprise)).get());
        return mv;
    }
    @PostMapping("/add/{id}")
    @Transactional
    public String addJournal(@PathVariable("id")String identreprise,@ModelAttribute Journal journal){
        journalRepository.save(journal);
        return "redirect:/Journal/journal.html/"+identreprise;
    }

    @GetMapping("/update/{id}")
    @Transactional
    public ModelAndView updateJournal(@PathVariable("id")String identreprise,@RequestParam String id){
        Journal j = journalRepository.findById(Long.valueOf(id)).get();
        ModelAndView mv = new ModelAndView("journal");
        mv.addObject("journals",journalRepository.findByidEntreprise(Long.valueOf(identreprise)));
        mv.addObject("vao",j);
        mv.addObject("obj",entreprise.findById(Long.valueOf(identreprise)).get());
        return mv;
    }
    @PostMapping("/list")
    public @ResponseBody Iterable<Journal> getAllJournal(){
        return journalRepository.findAll();
    }

    @GetMapping("/delete/{id}")
    public String deleteJournal(@PathVariable("id")String identreprise,@RequestParam String id){
        Journal j = journalRepository.findById(Long.valueOf(id)).get();
        journalRepository.delete(j);
        return "redirect:/Journal/journal.html/"+identreprise;
    }

    @GetMapping("/em/{id}")
    public ModelAndView getExerciceMois(@PathVariable("id")String identreprise){
        ArrayList<ExerciceMois> listEM = getExerciceJournalMois(identreprise,"1");
        ModelAndView mv = new ModelAndView("exMois");
        mv.addObject("exMois",listEM);
        return mv;
    }
    public ArrayList<ExerciceMois> getExerciceJournalMois(String identreprise,String idex){
        long id = 1;
        Exercice ex = exercice.findById(Long.valueOf(idex)).get();
        List<Journal> listeJournal = journalRepository.findByidEntreprise(ex.getIdentreprise());
        ArrayList<ExerciceMois> listEM = new ArrayList();
        for(int i =1;i<=12;i++){
            for(Journal j : listeJournal){
                listEM.add(new ExerciceMois(ex,i,j));
            }
        }
        return listEM;
    }
}
