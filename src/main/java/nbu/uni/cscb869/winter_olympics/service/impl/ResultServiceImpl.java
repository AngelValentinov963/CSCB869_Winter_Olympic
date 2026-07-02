package nbu.uni.cscb869.winter_olympics.service.impl;

import nbu.uni.cscb869.winter_olympics.data.entity.Result.SlalomResult;
import nbu.uni.cscb869.winter_olympics.data.repository.SlalomResultRepository;
import nbu.uni.cscb869.winter_olympics.dto.SlalomDTO.SlalomResultDTO;
import nbu.uni.cscb869.winter_olympics.service.ResultService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    private final SlalomResultRepository slalomResultRepository;

    public ResultServiceImpl(SlalomResultRepository slalomResultRepository) {
        this.slalomResultRepository = slalomResultRepository;
    }

    @Override
    public List<SlalomResultDTO> getSlalomResults(Long competitionId) {

        List<SlalomResult> results = slalomResultRepository.findByCompetitionId(competitionId);

        List<SlalomResultDTO> ranking = results.stream()
                .filter(r -> r.getFirstRunTime() != null && r.getSecondRunTime() != null)
                .map(r -> {
                    double total = r.getFirstRunTime() + r.getSecondRunTime();

                    SlalomResultDTO dto = new SlalomResultDTO();
                    dto.setAthleteId(r.getAthlete().getId());
                    dto.setAthleteFirstName(r.getAthlete().getFirstName());
                    dto.setAthleteLastName(r.getAthlete().getLastName());
                    dto.setCountry(r.getAthlete().getCountry());

                    dto.setFirstRunTime(r.getFirstRunTime());
                    dto.setSecondRunTime(r.getSecondRunTime());
                    dto.setTotalTime(total);

                    return dto;
                })
                .sorted(Comparator.comparing(SlalomResultDTO::getTotalTime))
                .toList();

        // assign ranks
        for (int i = 0; i < ranking.size(); i++) {
            ranking.get(i).setRank(i + 1);
        }

        return ranking;
    }

    @Override
    public List<?> getBiathlonFinalRanking(Long competitionId) {
        return List.of();
    }
}
