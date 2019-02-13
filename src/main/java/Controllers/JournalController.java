package Controllers;

import Model.Journal;
import DAO.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/Journal")
public class JournalController {
    @Autowired
    private JournalRepository journalRepository;

    @GetMapping("/journal.html")
    public ModelAndView loadView(){
        ModelAndView mv = new ModelAndView("journal");
        mv.addObject("journals",journalRepository.findAll());
        Journal j = new Journal();
        j.setIdEntreprise(1);
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

}
