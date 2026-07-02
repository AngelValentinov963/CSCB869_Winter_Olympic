package nbu.uni.cscb869.winter_olympics.service.impl;

import nbu.uni.cscb869.winter_olympics.data.entity.Competition.Competition;
import nbu.uni.cscb869.winter_olympics.data.entity.Participation;
import nbu.uni.cscb869.winter_olympics.data.entity.Result.Result;
import nbu.uni.cscb869.winter_olympics.data.repository.CompetitionRepository;
import nbu.uni.cscb869.winter_olympics.data.repository.ResultRepository;
import nbu.uni.cscb869.winter_olympics.dto.CompetitionDTO;
import nbu.uni.cscb869.winter_olympics.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final CompetitionRepository competitionRepository;
    private final ResultRepository resultRepository;

    public StatisticsServiceImpl(CompetitionRepository competitionRepository,
                                 ResultRepository resultRepository) {
        this.competitionRepository = competitionRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public List<CompetitionDTO> getAllCompetitions() {
        return competitionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CompetitionDTO convertToDTO(Competition competition) {
        CompetitionDTO dto = new CompetitionDTO();
        dto.setId(competition.getId());
        dto.setName(competition.getName());
        dto.setMinAge(competition.getMinAge());
        dto.setAllowedGender(competition.getAllowedGender().name());
        return dto;
    }


}
