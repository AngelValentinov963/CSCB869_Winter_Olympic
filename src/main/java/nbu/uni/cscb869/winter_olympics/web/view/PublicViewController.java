package nbu.uni.cscb869.winter_olympics.web.view;

import nbu.uni.cscb869.winter_olympics.service.StatisticsService; // Примерна услуга
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicViewController {

    private final StatisticsService statisticsService;

    public PublicViewController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    // Класиране по медали
    //@GetMapping("/rankings")
    //public String showRankings(Model model) {
    //    model.addAttribute("medalTable", statisticsService.getMedalTable());
    //    return "rankings"; // Търси rankings.html в templates
    //}

    // Списък със състезания
    @GetMapping("/competitions")
    public String showCompetitions(Model model) {
        model.addAttribute("competitions", statisticsService.getAllCompetitions());
        return "competitions"; // Търси competitions.html в templates
    }
}