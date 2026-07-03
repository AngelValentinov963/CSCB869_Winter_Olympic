package nbu.uni.cscb869.winter_olympics.web.view;


import nbu.uni.cscb869.winter_olympics.dto.Athlete.AthleteCreateDTO;
import nbu.uni.cscb869.winter_olympics.service.AthleteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/athlete")
public class AthleteViewController {
    private final AthleteService athleteService;



    public AthleteViewController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping({"", "/"})
    public String showRegisterForm() {
        return "athlete/athlete_register";
    }


    @PostMapping
    public String registerAthlete(@ModelAttribute AthleteCreateDTO athleteDTO) {

        athleteService.registerAthlete(athleteDTO);

        return "redirect:/";
    }
}