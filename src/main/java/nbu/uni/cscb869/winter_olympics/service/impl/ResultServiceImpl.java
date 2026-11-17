
package nbu.uni.cscb869.winter_olympics.service.impl;

import nbu.uni.cscb869.winter_olympics.data.entity.Result.SlalomResult;
import nbu.uni.cscb869.winter_olympics.data.repository.SlalomResultRepository;
import nbu.uni.cscb869.winter_olympics.dto.SlalomResultDTO;
import nbu.uni.cscb869.winter_olympics.dto.BiathlonResultDTO;
import nbu.uni.cscb869.winter_olympics.service.BiathlonService;
import nbu.uni.cscb869.winter_olympics.service.ResultService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ResultServiceImpl implements ResultService {

    private final SlalomResultRepository slalomResultRepository;
    private final BiathlonService biathlonService;

    public ResultServiceImpl(
            SlalomResultRepository slalomResultRepository,
            BiathlonService biathlonService
    ) {
        this.slalomResultRepository = slalomResultRepository;
        this.biathlonService = biathlonService;
    }

    @Override
    public List<SlalomResultDTO> getSlalomResults(Long competitionId) {

        if (competitionId == null) {
            throw new IllegalArgumentException(
                    "Competition ID cannot be null."
            );
        }

        List<SlalomResult> results =
                slalomResultRepository.findByCompetitionId(competitionId);

        List<SlalomResultDTO> ranking = results.stream()
                .filter(result ->
                        result.getFirstRunTime() != null
                                && result.getSecondRunTime() != null
                )
                .map(result -> {

                    BigDecimal totalTime =
                            result.getFirstRunTime()
                                    .add(result.getSecondRunTime());

                    SlalomResultDTO dto = new SlalomResultDTO();

                    dto.setAthleteId(result.getAthlete().getId());
                    dto.setAthleteFirstName(
                            result.getAthlete().getFirstName()
                    );
                    dto.setAthleteLastName(
                            result.getAthlete().getLastName()
                    );
                    dto.setCountry(result.getAthlete().getCountry());

                    dto.setFirstRunTime(result.getFirstRunTime());
                    dto.setSecondRunTime(result.getSecondRunTime());
                    dto.setTotalTime(totalTime);

                    return dto;
                })
                .sorted(Comparator.comparing(
                        SlalomResultDTO::getTotalTime
                ))
                .toList();

        for (int i = 0; i < ranking.size(); i++) {
            ranking.get(i).setRank(i + 1);
        }

        return ranking;
    }

    @Override
    public List<BiathlonResultDTO> getBiathlonFinalRanking(
            Long competitionId) {

        if (competitionId == null) {
            throw new IllegalArgumentException(
                    "Competition ID cannot be null."
            );
        }

        return biathlonService.calculateRanking(competitionId);
    }
}

