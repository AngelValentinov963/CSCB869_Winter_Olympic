package nbu.uni.cscb869.winter_olympics.service;

import nbu.uni.cscb869.winter_olympics.dto.BiathlonDTO.BiathlonResultDTO;
import nbu.uni.cscb869.winter_olympics.dto.BiathlonDTO.BiathlonInputDTO;

import java.util.List;
public interface BiathlonService {

    void enterSkiTime(Long competitionId, Long athleteId, Double skiTime);

    void enterMisses(Long competitionId, Long athleteId, Integer misses);

    List<BiathlonResultDTO> calculateRanking(Long competitionId);

    //calculate final result for a single athlete
    BiathlonResultDTO calculateAthleteFinalResult(Long competitionId, BiathlonInputDTO dto);

}