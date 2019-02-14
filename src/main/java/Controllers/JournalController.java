package Controllers;

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

    @GetMapping("/journal.html")
    public ModelAndView loadView(){
        ModelAndView mv = new ModelAndView("journal");
        mv.addObject("journals",journalRepository.findAll());
        Journal j = new Journal();
        j.setIdEntreprise(new Long(1));
        mv.addObject("vao",j);
        return mv;
    }
    @PostMapping("/add")
    @Transactional
    public String addJournal(@ModelAttribute Journal journal){
        journalRepository.save(journal);
        return "redirect:/Journal/journal.html";
    }

    @GetMapping("/update")
    @Transactional
    public ModelAndView updateJournal(@RequestParam int id){
        Journal j = journalRepository.findById(id).get();
        ModelAndView mv = new ModelAndView("journal");
        mv.addObject("journals",journalRepository.findAll());
        mv.addObject("vao",j);
        return mv;
    }
    @PostMapping("/list")
    public @ResponseBody Iterable<Journal> getAllJournal(){
        return journalRepository.findAll();
    }

    @GetMapping("/delete")
    public String deleteJournal(@RequestParam int id){
        Journal j = journalRepository.findById(id).get();
        journalRepository.delete(j);
        return "redirect:/Journal/journal.html";
    }

    @GetMapping("/ExerciceMois")
    public ModelAndView getExerciceMois(){
        ArrayList<ExerciceMois> listEM = getExerciceJournalMois();
        ModelAndView mv = new ModelAndView("exMois");
        mv.addObject("exMois",listEM);
        return mv;
    }
    public ArrayList<ExerciceMois> getExerciceJournalMois(){
        long id = 1;
        Exercice ex = exercice.findById(id).get();
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
