package nbu.uni.cscb869.winter_olympics.web.view;

import org.springframework.ui.Model;
import nbu.uni.cscb869.winter_olympics.service.ResultService;
import nbu.uni.cscb869.winter_olympics.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class StatisticsViewController {

    private final StatisticsService statisticsService;
    private final ResultService resultService;

    public StatisticsViewController(StatisticsService statisticsService,
                                    ResultService resultService) {
        this.statisticsService = statisticsService;
        this.resultService = resultService;
    }

    @GetMapping
    public String statistics(Model model) {

        model.addAttribute("competitions",
                statisticsService.getAllCompetitions());

        model.addAttribute("medalTable",
                statisticsService.getMedalTable());

        model.addAttribute("averageAge",
                statisticsService.getAverageAge());

        model.addAttribute("youngestMedalist",
                statisticsService.getYoungestMedalist());

        model.addAttribute("oldestMedalist",
                statisticsService.getOldestMedalist());

        return "statistics/index";
    }

    @GetMapping("/{competitionId}")
    public String competitionResults(@PathVariable Long competitionId,
                                     Model model) {

        model.addAttribute("results",
                resultService.getSlalomResults(competitionId));

        return "statistics/results";
    }
}