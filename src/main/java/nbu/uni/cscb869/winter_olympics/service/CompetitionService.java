package nbu.uni.cscb869.winter_olympics.service;

import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.entity.Competition.Competition;

import java.util.List;

public interface CompetitionService {

    Competition create(Competition competition);

    Competition update(Long id, Competition competition);

    Competition getById(Long id);

    List<Competition> getAll();

    boolean registerAthlete(Long competitionId, Long athleteId);

    boolean unregisterAthlete(Long competitionId, Long athleteId);

    boolean validateAthlete(Long competitionId, Athlete athlete);

    void delete(Long id);

}