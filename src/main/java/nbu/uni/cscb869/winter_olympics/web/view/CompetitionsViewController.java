package nbu.uni.cscb869.winter_olympics.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompetitionsViewController {

    @GetMapping("/competitionss")
    public String competitions() {
        return "competitions";
    }




}
