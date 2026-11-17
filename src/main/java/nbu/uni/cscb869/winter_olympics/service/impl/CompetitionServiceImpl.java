package nbu.uni.cscb869.winter_olympics.service.impl;

import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.entity.Competition.Competition;
import nbu.uni.cscb869.winter_olympics.data.entity.Participation;
import nbu.uni.cscb869.winter_olympics.data.repository.AthleteRepository;
import nbu.uni.cscb869.winter_olympics.data.repository.CompetitionRepository;
import nbu.uni.cscb869.winter_olympics.data.repository.ParticipationRepository;
import nbu.uni.cscb869.winter_olympics.service.CompetitionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@Transactional
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final AthleteRepository athleteRepository;
    private final ParticipationRepository participationRepository;

    public CompetitionServiceImpl(
            CompetitionRepository competitionRepository,
            AthleteRepository athleteRepository,
            ParticipationRepository participationRepository
    ) {
        this.competitionRepository = competitionRepository;
        this.athleteRepository = athleteRepository;
        this.participationRepository = participationRepository;
    }

    @Override
    public Competition create(Competition competition) {
        if (competition == null) {
            throw new IllegalArgumentException(
                    "Competition cannot be null."
            );
        }

        validateCompetition(competition);
        competition.setId(null);

        return competitionRepository.save(competition);
    }

    @Override
    public Competition update(Long id, Competition updatedCompetition) {
        if (updatedCompetition == null) {
            throw new IllegalArgumentException(
                    "Competition cannot be null."
            );
        }

        validateCompetition(updatedCompetition);

        Competition existingCompetition = getById(id);

        existingCompetition.setName(updatedCompetition.getName());
        existingCompetition.setMinAge(updatedCompetition.getMinAge());
        existingCompetition.setAllowedGender(
                updatedCompetition.getAllowedGender()
        );

        if (updatedCompetition.getOlympic() != null) {
            existingCompetition.setOlympic(
                    updatedCompetition.getOlympic()
            );
        }

        return competitionRepository.save(existingCompetition);
    }

    @Override
    @Transactional(readOnly = true)
    public Competition getById(Long id) {
        return competitionRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Competition with ID " + id + " does not exist."
                        )
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Competition> getAll() {
        return competitionRepository.findAll();
    }

    @Override
    public boolean registerAthlete(Long competitionId, Long athleteId) {
        Competition competition = getById(competitionId);

        Athlete athlete = athleteRepository.findById(athleteId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Athlete with ID " + athleteId + " does not exist."
                        )
                );

        if (!validateAthlete(competitionId, athlete)) {
            return false;
        }

        boolean alreadyRegistered =
                participationRepository
                        .existsByCompetitionIdAndAthleteId(
                                competitionId,
                                athleteId
                        );

        if (alreadyRegistered) {
            return false;
        }

        Participation participation = new Participation();
        participation.setAthlete(athlete);
        participation.setCompetition(competition);
        participation.setDidNotFinish(false);

        participationRepository.save(participation);

        return true;
    }

    @Override
    public boolean unregisterAthlete(Long competitionId, Long athleteId) {
        getById(competitionId);

        Participation participation = participationRepository
                .findByCompetitionIdAndAthleteId(
                        competitionId,
                        athleteId
                )
                .orElse(null);

        if (participation == null) {
            return false;
        }

        participationRepository.delete(participation);

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateAthlete(Long competitionId, Athlete athlete) {
        if (athlete == null || athlete.getBirthDate() == null
                || athlete.getGender() == null) {
            return false;
        }

        Competition competition = getById(competitionId);

        if (athlete.getGender() != competition.getAllowedGender()) {
            return false;
        }

        LocalDate referenceDate = LocalDate.now();

        if (competition.getOlympic() != null
                && competition.getOlympic().getYear() != null) {
            referenceDate = competition.getOlympic()
                    .getYear()
                    .toLocalDate();
        }

        if (athlete.getBirthDate().isAfter(referenceDate)) {
            return false;
        }

        int age = Period.between(
                athlete.getBirthDate(),
                referenceDate
        ).getYears();

        return age >= competition.getMinAge();
    }

    @Override
    public void delete(Long id) {
        Competition competition = getById(id);
        competitionRepository.delete(competition);
    }


    private void validateCompetition(Competition competition) {
        if (competition.getName() == null
                || competition.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "Competition name is required."
            );
        }

        if (competition.getMinAge() < 0) {
            throw new IllegalArgumentException(
                    "Minimum age cannot be negative."
            );
        }

        if (competition.getAllowedGender() == null) {
            throw new IllegalArgumentException(
                    "Competition gender is required."
            );
        }
    }
}

