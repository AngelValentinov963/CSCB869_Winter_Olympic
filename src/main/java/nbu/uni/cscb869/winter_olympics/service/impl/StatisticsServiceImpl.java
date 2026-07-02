package nbu.uni.cscb869.winter_olympics.service.impl;

import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.entity.Competition.Competition;
import nbu.uni.cscb869.winter_olympics.data.entity.Result.Result;
import nbu.uni.cscb869.winter_olympics.data.repository.CompetitionRepository;
import nbu.uni.cscb869.winter_olympics.data.repository.ResultRepository;
import nbu.uni.cscb869.winter_olympics.dto.AthleteDTO;
import nbu.uni.cscb869.winter_olympics.dto.CompetitionDTO;
import nbu.uni.cscb869.winter_olympics.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                .toList();
    }

    @Override
    public Map<String, Integer> getMedalTable() {

        Map<String, Integer> medals = new HashMap<>();

        List<Result> results = resultRepository.findAll();

        for (Competition competition : competitionRepository.findAll()) {

            List<Result> sorted = results.stream()
                    .filter(r -> r.getCompetition().getId() == competition.getId())
                    .filter(r -> r.getFinalTime() != null)
                    .sorted(Comparator.comparing(Result::getFinalTime))
                    .toList();

            for (int i = 0; i < Math.min(3, sorted.size()); i++) {
                String country = sorted.get(i).getAthlete().getCountry();
                medals.put(country, medals.getOrDefault(country, 0) + 1);
            }
        }

        return medals;
    }

    @Override
    public double getAverageAge() {

        return resultRepository.findAll().stream()
                .map(Result::getAthlete)
                .distinct()
                .mapToInt(a -> java.time.Period.between(
                        a.getBirthDate(),
                        java.time.LocalDate.now()
                ).getYears())
                .average()
                .orElse(0);
    }

    @Override
    public AthleteDTO getYoungestMedalist() {

        return resultRepository.findAll().stream()
                .map(Result::getAthlete)
                .distinct()
                .min(Comparator.comparing(Athlete::getBirthDate))
                .map(this::toDTO)
                .orElse(null);
    }

    @Override
    public AthleteDTO getOldestMedalist() {

        return resultRepository.findAll().stream()
                .map(Result::getAthlete)
                .distinct()
                .max(Comparator.comparing(Athlete::getBirthDate))
                .map(this::toDTO)
                .orElse(null);
    }

    private CompetitionDTO convertToDTO(Competition competition) {
        CompetitionDTO dto = new CompetitionDTO();
        dto.setId(competition.getId());
        dto.setName(competition.getName());
        dto.setMinAge(competition.getMinAge());
        dto.setAllowedGender(competition.getAllowedGender().name());
        return dto;
    }

    private AthleteDTO toDTO(Athlete a) {
        AthleteDTO dto = new AthleteDTO();
        dto.setId(a.getId());
        dto.setFirstName(a.getFirstName());
        dto.setLastName(a.getLastName());
        dto.setCountry(a.getCountry());
        return dto;
    }
}