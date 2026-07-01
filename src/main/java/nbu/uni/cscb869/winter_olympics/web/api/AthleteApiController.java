package nbu.uni.cscb869.winter_olympics.web.api;

import nbu.uni.cscb869.winter_olympics.service.AthleteService;

public class AthleteApiController {
    private final AthleteService athleteService;

    public AthleteApiController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }
}
