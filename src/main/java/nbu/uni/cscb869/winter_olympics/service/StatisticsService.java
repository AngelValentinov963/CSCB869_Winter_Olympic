package nbu.uni.cscb869.winter_olympics.service;

import nbu.uni.cscb869.winter_olympics.dto.Athlete.AthleteDTO;
import nbu.uni.cscb869.winter_olympics.dto.CompetitionDTO.CompetitionDTO;

import java.util.List;
import java.util.Map;

public interface StatisticsService {

    List<CompetitionDTO> getAllCompetitions();

    Map<String, Integer> getMedalTable();

    double getAverageAge();

    AthleteDTO getYoungestMedalist();

    AthleteDTO getOldestMedalist();
}