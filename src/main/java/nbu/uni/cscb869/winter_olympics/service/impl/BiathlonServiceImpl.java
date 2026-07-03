package nbu.uni.cscb869.winter_olympics.service.impl;

import lombok.RequiredArgsConstructor;
import nbu.uni.cscb869.winter_olympics.data.entity.Result.BiathlonResult;
import nbu.uni.cscb869.winter_olympics.data.repository.BiathlonResultRepository;
import nbu.uni.cscb869.winter_olympics.dto.BiathlonDTO.BiathlonResultDTO;
import nbu.uni.cscb869.winter_olympics.dto.BiathlonDTO.BiathlonInputDTO;
import nbu.uni.cscb869.winter_olympics.service.BiathlonService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BiathlonServiceImpl implements BiathlonService {

    private static final int PENALTY_PER_MISS = 60;

    private final BiathlonResultRepository biathlonResultRepository;

    @Override
    public void enterSkiTime(Long competitionId, Long athleteId, Double skiTime) {

        BiathlonResult result = biathlonResultRepository
                .findByCompetitionIdAndAthleteId(competitionId, athleteId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Result not found"));

        result.setSkiTime(skiTime);

        biathlonResultRepository.save(result);
    }

    @Override
    public void enterMisses(Long competitionId, Long athleteId, Integer misses) {

        BiathlonResult result = biathlonResultRepository
                .findByCompetitionIdAndAthleteId(competitionId, athleteId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Result not found"));

        result.setMisses(misses);
        result.setPenaltySeconds(misses * PENALTY_PER_MISS);
        result.setFinalTime(result.getSkiTime() + result.getPenaltySeconds());

        biathlonResultRepository.save(result);
    }

    @Override
    public List<BiathlonResultDTO> calculateRanking(Long competitionId) {

        List<BiathlonResult> results =
                biathlonResultRepository.findByCompetitionId(competitionId);

        return results.stream()
                .filter(BiathlonResult::getFinished)
                .sorted(Comparator.comparing(BiathlonResult::getFinalTime))
                .map(this::toDto)
                .toList();

    }

    // TODO: throw AthleteNotFoundException

    @Override
    public BiathlonResultDTO calculateAthleteFinalResult(Long competitionId, BiathlonInputDTO dto) {

        BiathlonResult result = biathlonResultRepository
                .findByCompetitionIdAndAthleteId(
                        competitionId,
                        dto.getAthleteId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Result not found"));

        result.setPenaltySeconds(result.getMisses() * PENALTY_PER_MISS);
        result.setFinalTime(result.getSkiTime() + result.getPenaltySeconds());

        biathlonResultRepository.save(result);

        return toDto(result);
    }

    private BiathlonResultDTO toDto(BiathlonResult result) {

        BiathlonResultDTO dto = new BiathlonResultDTO();

        dto.setAthleteId(result.getAthlete().getId());
        dto.setAthleteFirstName(result.getAthlete().getFirstName());
        dto.setAthleteLastName(result.getAthlete().getLastName());

        dto.setSkiingTimeSeconds(result.getSkiTime());

        dto.setMissedShots(result.getMisses());

        dto.setFinished(result.getFinished());

        return dto;
    }
}