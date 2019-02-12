package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@Controller
public class AccueilController {
    @GetMapping("/")
    public String getAccueil(){
        return "index";
    }

    @GetMapping("/comptesTiers")
    public ModelAndView comptesTiers(){
        ModelAndView md=new ModelAndView("pageCompteTiers");
        return md;
      }

}
