
package nbu.uni.cscb869.winter_olympics.service.impl;

import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.entity.Competition.Competition;
import nbu.uni.cscb869.winter_olympics.data.entity.Olympic;
import nbu.uni.cscb869.winter_olympics.data.entity.Participation;
import nbu.uni.cscb869.winter_olympics.data.entity.Result.Result;
import nbu.uni.cscb869.winter_olympics.data.repository.CompetitionRepository;
import nbu.uni.cscb869.winter_olympics.data.repository.ResultRepository;
import nbu.uni.cscb869.winter_olympics.dto.AthleteDTO;
import nbu.uni.cscb869.winter_olympics.dto.CompetitionDTO;
import nbu.uni.cscb869.winter_olympics.service.StatisticsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

@Service
@Transactional(readOnly = true)
public class StatisticsServiceImpl implements StatisticsService {

    private final CompetitionRepository competitionRepository;
    private final ResultRepository resultRepository;

    public StatisticsServiceImpl(
            CompetitionRepository competitionRepository,
            ResultRepository resultRepository
    ) {
        this.competitionRepository = competitionRepository;
        this.resultRepository = resultRepository;
    }

    // ==========================================
    // ALL COMPETITIONS
    // ==========================================

    @Override
    public List<CompetitionDTO> getAllCompetitions() {

        return competitionRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // ==========================================
    // MEDAL TABLE
    // ==========================================

    @Override
    public Map<String, Integer> getMedalTable() {

        Map<String, Integer> medals = new TreeMap<>();

        List<Competition> competitions =
                competitionRepository.findAll();

        List<Result> allResults =
                resultRepository.findAll();

        for (Competition competition : competitions) {

            List<Result> ranking = getCompletedResultsForCompetition(
                    competition.getId(),
                    allResults
            );

            // Първите трима получават медали.
            int medalPlaces = Math.min(3, ranking.size());

            for (int i = 0; i < medalPlaces; i++) {

                Athlete athlete = ranking.get(i).getAthlete();

                if (athlete == null || athlete.getCountry() == null) {
                    continue;
                }

                String country = athlete.getCountry();

                medals.put(
                        country,
                        medals.getOrDefault(country, 0) + 1
                );
            }
        }

        return medals;
    }

    // ==========================================
    // AVERAGE ATHLETE AGE
    // ==========================================

    @Override
    public double getAverageAge() {

        Map<Long, Athlete> athletes = new LinkedHashMap<>();

        List<Competition> competitions =
                competitionRepository.findAll();

        for (Competition competition : competitions) {

            if (competition.getParticipations() == null) {
                continue;
            }

            for (Participation participation :
                    competition.getParticipations()) {

                Athlete athlete = participation.getAthlete();

                if (athlete != null && athlete.getId() != null) {
                    athletes.putIfAbsent(athlete.getId(), athlete);
                }
            }
        }

        LocalDate referenceDate = getOlympicDate(competitions);

        return athletes.values()
                .stream()
                .filter(a -> a.getBirthDate() != null)
                .mapToInt(a ->
                        Period.between(
                                a.getBirthDate(),
                                referenceDate
                        ).getYears()
                )
                .average()
                .orElse(0.0);
    }

    // ==========================================
    // YOUNGEST MEDALIST
    // ==========================================

    @Override
    public AthleteDTO getYoungestMedalist() {

        return getMedalists()
                .stream()
                .min(Comparator.comparing(Athlete::getBirthDate))
                .map(this::toDTO)
                .orElse(null);
    }

    // ==========================================
    // OLDEST MEDALIST
    // ==========================================

    @Override
    public AthleteDTO getOldestMedalist() {

        return getMedalists()
                .stream()
                .max(Comparator.comparing(Athlete::getBirthDate))
                .map(this::toDTO)
                .orElse(null);
    }

    // ==========================================
    // HELPER: COMPLETED RESULTS
    // ==========================================

    private List<Result> getCompletedResultsForCompetition(
            Long competitionId,
            List<Result> allResults
    ) {

        return allResults.stream()
                .filter(result ->
                        result.getCompetition() != null
                                && Objects.equals(
                                result.getCompetition().getId(),
                                competitionId
                        )
                )
                .filter(result ->
                        Boolean.TRUE.equals(result.getFinished())
                )
                .filter(result -> result.getFinalTime() != null)
                .sorted(Comparator.comparing(Result::getFinalTime))
                .toList();
    }

    // ==========================================
    // HELPER: ALL MEDALISTS
    // ==========================================

    private List<Athlete> getMedalists() {

        Map<Long, Athlete> medalists = new LinkedHashMap<>();

        List<Competition> competitions =
                competitionRepository.findAll();

        List<Result> allResults =
                resultRepository.findAll();

        for (Competition competition : competitions) {

            List<Result> ranking = getCompletedResultsForCompetition(
                    competition.getId(),
                    allResults
            );

            int medalPlaces = Math.min(3, ranking.size());

            for (int i = 0; i < medalPlaces; i++) {

                Athlete athlete = ranking.get(i).getAthlete();

                if (athlete != null && athlete.getId() != null) {
                    medalists.putIfAbsent(
                            athlete.getId(),
                            athlete
                    );
                }
            }
        }

        return List.copyOf(medalists.values());
    }

    // ==========================================
    // HELPER: OLYMPIC DATE
    // ==========================================

    private LocalDate getOlympicDate(
            List<Competition> competitions
    ) {

        return competitions.stream()
                .map(Competition::getOlympic)
                .filter(Objects::nonNull)
                .map(Olympic::getYear)
                .filter(Objects::nonNull)
                .map(java.time.LocalDateTime::toLocalDate)
                .findFirst()
                .orElse(LocalDate.now());
    }

    // ==========================================
    // HELPER: COMPETITION DTO
    // ==========================================

    private CompetitionDTO convertToDTO(Competition competition) {

        CompetitionDTO dto = new CompetitionDTO();

        dto.setId(competition.getId());
        dto.setName(competition.getName());
        dto.setMinAge(competition.getMinAge());

        if (competition.getAllowedGender() != null) {
            dto.setAllowedGender(
                    competition.getAllowedGender().name()
            );
        }

        return dto;
    }

    // ==========================================
    // HELPER: ATHLETE DTO
    // ==========================================

    private AthleteDTO toDTO(Athlete athlete) {

        AthleteDTO dto = new AthleteDTO();

        dto.setId(athlete.getId());
        dto.setFirstName(athlete.getFirstName());
        dto.setLastName(athlete.getLastName());
        dto.setCountry(athlete.getCountry());

        return dto;
    }
}
