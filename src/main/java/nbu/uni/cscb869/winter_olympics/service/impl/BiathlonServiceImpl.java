
package nbu.uni.cscb869.winter_olympics.service.impl;

import lombok.RequiredArgsConstructor;
import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.entity.Competition.BiathlonCompetition;
import nbu.uni.cscb869.winter_olympics.data.entity.Result.BiathlonResult;
import nbu.uni.cscb869.winter_olympics.data.repository.AthleteRepository;
import nbu.uni.cscb869.winter_olympics.data.repository.BiathlonResultRepository;
import nbu.uni.cscb869.winter_olympics.data.repository.CompetitionRepository;
import nbu.uni.cscb869.winter_olympics.data.repository.ParticipationRepository;
import nbu.uni.cscb869.winter_olympics.dto.BiathlonInputDTO;
import nbu.uni.cscb869.winter_olympics.dto.BiathlonResultDTO;
import nbu.uni.cscb869.winter_olympics.service.BiathlonService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BiathlonServiceImpl implements BiathlonService {

    private static final int PENALTY_PER_MISS = 60;

    private final BiathlonResultRepository biathlonResultRepository;
    private final CompetitionRepository competitionRepository;
    private final AthleteRepository athleteRepository;
    private final ParticipationRepository participationRepository;

    @Override
    public void enterSkiTime(Long competitionId, Long athleteId, Double skiTime) {

        if (skiTime == null || skiTime <= 0) {
            throw new IllegalArgumentException(
                    "Ski time must be greater than zero."
            );
        }

        BiathlonResult result = getOrCreateResult(competitionId, athleteId);

        result.setSkiTime(BigDecimal.valueOf(skiTime));

        updateFinalTime(result);

        biathlonResultRepository.save(result);
    }

    @Override
    public void enterMisses(Long competitionId, Long athleteId, Integer misses) {

        if (misses == null || misses < 0) {
            throw new IllegalArgumentException(
                    "Misses cannot be negative."
            );
        }

        BiathlonResult result = getOrCreateResult(competitionId, athleteId);

        result.setMisses(misses);

        updateFinalTime(result);

        biathlonResultRepository.save(result);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BiathlonResultDTO> calculateRanking(Long competitionId) {

        getBiathlonCompetition(competitionId);

        List<BiathlonResult> results =
                biathlonResultRepository.findByCompetitionId(competitionId);

        List<BiathlonResult> rankedResults = results.stream()
                .filter(result -> Boolean.TRUE.equals(result.getFinished()))
                .filter(result -> result.getFinalTime() != null)
                .sorted(Comparator.comparing(BiathlonResult::getFinalTime))
                .toList();

        List<BiathlonResultDTO> ranking = new ArrayList<>();

        for (int i = 0; i < rankedResults.size(); i++) {
            BiathlonResultDTO dto = toDto(rankedResults.get(i));
            dto.setRank(i + 1);
            ranking.add(dto);
        }

        return ranking;
    }

    @Override
    public BiathlonResultDTO calculateAthleteFinalResult(
            Long competitionId,
            BiathlonInputDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException(
                    "Biathlon input cannot be null."
            );
        }

        if (dto.getAthleteId() == null) {
            throw new IllegalArgumentException(
                    "Athlete ID is required."
            );
        }

        if (dto.getSkiingTimeSeconds() == null
                || dto.getSkiingTimeSeconds().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Skiing time must be greater than zero."
            );
        }

        if (dto.getMissedShots() == null || dto.getMissedShots() < 0) {
            throw new IllegalArgumentException(
                    "Missed shots cannot be negative."
            );
        }

        if (dto.getLaps() == null || dto.getLaps() < 1) {
            throw new IllegalArgumentException(
                    "Number of laps must be at least one."
            );
        }

        if (dto.getFinished() == null) {
            throw new IllegalArgumentException(
                    "Finished status is required."
            );
        }

        BiathlonResult result = getOrCreateResult(
                competitionId,
                dto.getAthleteId()
        );

        result.setSkiTime(dto.getSkiingTimeSeconds());
        result.setLaps(dto.getLaps());
        result.setMisses(dto.getMissedShots());
        result.setPenaltySeconds(
                dto.getMissedShots() * PENALTY_PER_MISS
        );
        result.setFinished(dto.getFinished());

        updateFinalTime(result);

        BiathlonResult savedResult =
                biathlonResultRepository.save(result);

        return toDto(savedResult);
    }

    private BiathlonResult getOrCreateResult(
            Long competitionId,
            Long athleteId) {

        if (competitionId == null || athleteId == null) {
            throw new IllegalArgumentException(
                    "Competition ID and athlete ID are required."
            );
        }

        BiathlonCompetition competition =
                getBiathlonCompetition(competitionId);

        Athlete athlete = athleteRepository.findById(athleteId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Athlete not found with ID: " + athleteId
                ));

        boolean registered = participationRepository
                .existsByCompetitionIdAndAthleteId(
                        competitionId,
                        athleteId
                );

        if (!registered) {
            throw new IllegalArgumentException(
                    "Athlete is not registered for this competition."
            );
        }

        return biathlonResultRepository
                .findByCompetitionIdAndAthleteId(
                        competitionId,
                        athleteId
                )
                .orElseGet(() -> {
                    BiathlonResult newResult = new BiathlonResult();

                    newResult.setCompetition(competition);
                    newResult.setAthlete(athlete);
                    newResult.setMisses(0);
                    newResult.setTotalShots(0);
                    newResult.setPenaltySeconds(0);
                    newResult.setFinished(false);

                    return newResult;
                });
    }

    private BiathlonCompetition getBiathlonCompetition(Long competitionId) {

        return competitionRepository.findById(competitionId)
                .filter(BiathlonCompetition.class::isInstance)
                .map(BiathlonCompetition.class::cast)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Biathlon competition not found with ID: "
                                + competitionId
                ));
    }

    private void updateFinalTime(BiathlonResult result) {

        int misses = result.getMisses() == null
                ? 0
                : result.getMisses();

        int penaltySeconds = misses * PENALTY_PER_MISS;

        result.setPenaltySeconds(penaltySeconds);

        if (result.getSkiTime() == null
                || !Boolean.TRUE.equals(result.getFinished())) {

            result.setFinalTime(null);
            return;
        }

        BigDecimal penalty = BigDecimal.valueOf(penaltySeconds);

        BigDecimal finalTime = result.getSkiTime().add(penalty);

        result.setFinalTime(finalTime);
    }

    private BiathlonResultDTO toDto(BiathlonResult result) {

        BiathlonResultDTO dto = new BiathlonResultDTO();

        dto.setAthleteId(result.getAthlete().getId());
        dto.setAthleteFirstName(result.getAthlete().getFirstName());
        dto.setAthleteLastName(result.getAthlete().getLastName());
        dto.setCountry(result.getAthlete().getCountry());

        dto.setSkiingTimeSeconds(result.getSkiTime());
        dto.setTotalShots(result.getTotalShots());
        dto.setMissedShots(result.getMisses());
        dto.setPenaltySeconds(result.getPenaltySeconds());
        dto.setFinalTimeSeconds(result.getFinalTime());
        dto.setFinished(result.getFinished());

        return dto;
    }
}
