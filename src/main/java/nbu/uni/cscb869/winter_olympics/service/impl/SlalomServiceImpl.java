package nbu.uni.cscb869.winter_olympics.service.impl;

import nbu.uni.cscb869.winter_olympics.data.entity.Competition.Competition;
import nbu.uni.cscb869.winter_olympics.data.entity.Competition.SlalomCompetition;
import nbu.uni.cscb869.winter_olympics.data.entity.Result.SlalomResult;
import nbu.uni.cscb869.winter_olympics.data.repository.AthleteRepository;
import nbu.uni.cscb869.winter_olympics.data.repository.CompetitionRepository;
import nbu.uni.cscb869.winter_olympics.data.repository.ResultRepository;
import nbu.uni.cscb869.winter_olympics.data.repository.SlalomResultRepository;
import nbu.uni.cscb869.winter_olympics.dto.SlalomDTO.SlalomFirstRunDTO;
import nbu.uni.cscb869.winter_olympics.dto.SlalomDTO.SlalomResultDTO;
import nbu.uni.cscb869.winter_olympics.dto.SlalomDTO.SlalomSecondRunDTO;
import nbu.uni.cscb869.winter_olympics.service.SlalomService;

import java.util.Comparator;
import java.util.List;

public class SlalomServiceImpl implements SlalomService {
    private final ResultRepository resultRepository;
    private final CompetitionRepository competitionRepository;
    private final AthleteRepository athleteRepository;
    private final SlalomResultRepository slalomResultRepository; // Add this

    public SlalomServiceImpl(ResultRepository resultRepository,
                             CompetitionRepository competitionRepository,
                             AthleteRepository athleteRepository,
                             SlalomResultRepository slalomResultRepository) { // Update constructor
        this.resultRepository = resultRepository;
        this.competitionRepository = competitionRepository;
        this.athleteRepository = athleteRepository;
        this.slalomResultRepository = slalomResultRepository;
    }

    @Override
    public void submitFirstRun(SlalomFirstRunDTO dto) {

        SlalomResult result = slalomResultRepository
                .findByCompetitionIdAndAthleteId(dto.getCompetitionId(), dto.getAthleteId())
                .orElse(new SlalomResult());

        result.setCompetition(competitionRepository.getReferenceById(dto.getCompetitionId()));
        result.setAthlete(athleteRepository.getReferenceById(dto.getAthleteId()));
        result.setFirstRunTime(dto.getFirstRunTime());

        slalomResultRepository.save(result);
    }

    @Override
    public List<Long> getSecondRunStartOrder(Long competitionId) {
        Competition baseCompetition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        if (!(baseCompetition instanceof SlalomCompetition competition)) {
            throw new IllegalArgumentException("The requested competition is not a Slalom competition.");
        }

        int limit = competition.getFinalistCounts();

        return slalomResultRepository.findByCompetitionId(competitionId)
                .stream()
                .filter(r -> r.getFirstRunTime() != null)
                .sorted(Comparator.comparing(SlalomResult::getFirstRunTime))
                .limit(limit)
                .sorted(Comparator.comparing(SlalomResult::getFirstRunTime).reversed())
                .map(r -> r.getAthlete().getId())
                .toList();
    }

    @Override
    public void submitSecondRun(SlalomSecondRunDTO dto) {

        SlalomResult result = slalomResultRepository
                .findByCompetitionIdAndAthleteId(dto.getCompetitionId(), dto.getAthleteId())
                .orElseThrow();

        result.setSecondRunTime(dto.getSecondRunTime());

        slalomResultRepository.save(result);
    }

    @Override
    public List<SlalomResultDTO> getFinalRanking(Long competitionId) {

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
}
