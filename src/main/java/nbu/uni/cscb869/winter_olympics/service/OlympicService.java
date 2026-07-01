package nbu.uni.cscb869.winter_olympics.service;

import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;

import java.util.Map;

public interface OlympicService {
    //List<MedalDTO> getMedalists(Long olympicId);
    Map<String, Integer> getMedalistByCounty(String country);
    Athlete getYoungestMedalist(Long olympicId);
    Athlete getOldestMedalist(Long olympicId);

}
