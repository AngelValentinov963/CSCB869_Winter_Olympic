package nbu.uni.cscb869.winter_olympics.service.impl;

import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.entity.Competition.Competition;
import nbu.uni.cscb869.winter_olympics.data.repository.CompetitionRepository;
import nbu.uni.cscb869.winter_olympics.service.CompetitionService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class CompetitionServiceImpl implements CompetitionService {
    private CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Competition create(Competition competition) {
        return null;
    }

    @Override
    public Competition update(Long id, Competition competition) {
        return null;
    }

    @Override
    public Competition getById(Long id) {
        return null;
    }

    @Override
    public List<Competition> getAll() {
        return List.of();
    }

    @Override
    public boolean registerAthlete(Long competitionId, Long athleteId) {
        return false;
    }

    @Override
    public boolean unregisterAthlete(Long competitionId, Long athleteId) {
        return false;
    }

    @Override
    public boolean validateAthlete(Long competitionId, Athlete athlete) {
        Competition competition = competitionRepository.findById(competitionId).orElseThrow();

        if (athlete.getGender() != competition.getAllowedGender()) {
            return false;
        }

        int age = Period.between(athlete.getBirthDate(), LocalDate.now()).getYears();
        return age >= competition.getMinAge();
    }

    @Override
    public void delete(Long id) {

    }
}
