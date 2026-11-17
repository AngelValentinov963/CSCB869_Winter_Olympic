
package nbu.uni.cscb869.winter_olympics.service.impl;

import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.entity.Competition.Competition;
import nbu.uni.cscb869.winter_olympics.data.entity.Competition.SlalomCompetition;
import nbu.uni.cscb869.winter_olympics.data.entity.Result.SlalomResult;
import nbu.uni.cscb869.winter_olympics.data.repository.AthleteRepository;
import nbu.uni.cscb869.winter_olympics.data.repository.CompetitionRepository;
import nbu.uni.cscb869.winter_olympics.data.repository.ParticipationRepository;
import nbu.uni.cscb869.winter_olympics.data.repository.SlalomResultRepository;
import nbu.uni.cscb869.winter_olympics.dto.SlalomRunDTO;
import nbu.uni.cscb869.winter_olympics.dto.SlalomResultDTO;
import nbu.uni.cscb869.winter_olympics.service.SlalomService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class SlalomServiceImpl implements SlalomService {

    private final CompetitionRepository competitionRepository;
    private final AthleteRepository athleteRepository;
    private final ParticipationRepository participationRepository;
    private final SlalomResultRepository slalomResultRepository;

    public SlalomServiceImpl(
            CompetitionRepository competitionRepository,
            AthleteRepository athleteRepository,
            ParticipationRepository participationRepository,
            SlalomResultRepository slalomResultRepository
    ) {
        this.competitionRepository = competitionRepository;
        this.athleteRepository = athleteRepository;
        this.participationRepository = participationRepository;
        this.slalomResultRepository = slalomResultRepository;
    }

    // ==========================================
    // FIRST RUN
    // ==========================================

    @Override
    public void submitFirstRun(SlalomRunDTO dto) {

        validateDTO(dto);

        SlalomCompetition competition =
                getSlalomCompetition(dto.getCompetitionId());

        Athlete athlete = getAthlete(dto.getAthleteId());

        validateRegistration(
                dto.getCompetitionId(),
                dto.getAthleteId()
        );

        SlalomResult result = slalomResultRepository
                .findByCompetitionIdAndAthleteId(
                        dto.getCompetitionId(),
                        dto.getAthleteId()
                )
                .orElseGet(SlalomResult::new);

        if (result.isFirstRunSubmitted()) {
            throw new IllegalStateException(
                    "First-run result has already been submitted."
            );
        }

        result.setCompetition(competition);
        result.setAthlete(athlete);

        // Reset second-run and final-result data.
        result.setSecondRunTime(null);
        result.setSecondRunSubmitted(false);
        result.setFinalTime(null);
        result.setFinished(false);

        if (Boolean.TRUE.equals(dto.getDidNotFinish())) {

            result.setFirstRunTime(null);

        } else {

            validateTime(dto.getFirstRunTime());

            result.setFirstRunTime(dto.getFirstRunTime());
        }

        result.setFirstRunSubmitted(true);

        slalomResultRepository.save(result);
    }

    // ==========================================
    // SECOND RUN START ORDER
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public List<Long> getSecondRunStartOrder(Long competitionId) {

        SlalomCompetition competition =
                getSlalomCompetition(competitionId);

        int finalistCount = competition.getFinalistCounts();

        if (finalistCount <= 0) {
            throw new IllegalStateException(
                    "Finalist count must be greater than zero."
            );
        }

        List<SlalomResult> finalists = slalomResultRepository
                .findByCompetitionId(competitionId)
                .stream()
                .filter(result ->
                        result.isFirstRunSubmitted()
                                && result.getFirstRunTime() != null
                )
                .sorted(
                        Comparator.comparing(
                                SlalomResult::getFirstRunTime
                        )
                )
                .limit(finalistCount)
                .toList();

        // The fastest athlete starts last in the second run.
        return finalists.stream()
                .sorted(
                        Comparator.comparing(
                                SlalomResult::getFirstRunTime
                        ).reversed()
                )
                .map(result -> result.getAthlete().getId())
                .toList();
    }

    // ==========================================
    // SECOND RUN
    // ==========================================

    @Override
    public void submitSecondRun(SlalomRunDTO dto) {

        validateDTO(dto);

        getSlalomCompetition(dto.getCompetitionId());

        SlalomResult result = slalomResultRepository
                .findByCompetitionIdAndAthleteId(
                        dto.getCompetitionId(),
                        dto.getAthleteId()
                )
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "First-run result not found."
                        )
                );

        // Athlete must have finished the first run.
        if (!result.isFirstRunSubmitted()
                || result.getFirstRunTime() == null) {
            throw new IllegalStateException(
                    "Athlete did not finish the first run."
            );
        }

        // Check qualification for the second run.
        List<Long> startOrder =
                getSecondRunStartOrder(dto.getCompetitionId());

        if (!startOrder.contains(dto.getAthleteId())) {
            throw new IllegalArgumentException(
                    "Athlete did not qualify for the second run."
            );
        }

        // Prevent duplicate submissions.
        if (result.isSecondRunSubmitted()) {
            throw new IllegalStateException(
                    "Second-run result has already been submitted."
            );
        }

        if (Boolean.TRUE.equals(dto.getDidNotFinish())) {

            result.setSecondRunTime(null);
            result.setFinalTime(null);
            result.setFinished(false);

        } else {

            validateTime(dto.getFirstRunTime());

            result.setSecondRunTime(dto.getFirstRunTime());

            BigDecimal finalTime = result.getFirstRunTime()
                    .add(result.getSecondRunTime());

            result.setFinalTime(finalTime);
            result.setFinished(true);
        }

        result.setSecondRunSubmitted(true);

        slalomResultRepository.save(result);
    }

    // ==========================================
    // FINAL RANKING
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public List<SlalomResultDTO> getFinalRanking(Long competitionId) {

        getSlalomCompetition(competitionId);

        List<SlalomResult> results =
                slalomResultRepository.findByCompetitionId(competitionId);

        List<SlalomResultDTO> ranking = results.stream()
                .filter(result ->
                        result.isFirstRunSubmitted()
                                && result.isSecondRunSubmitted()
                                && Boolean.TRUE.equals(result.getFinished())
                                && result.getFirstRunTime() != null
                                && result.getSecondRunTime() != null
                                && result.getFinalTime() != null
                )
                .map(result -> {

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

                    // Use the final time stored in the database.
                    dto.setTotalTime(result.getFinalTime());

                    return dto;
                })
                .sorted(
                        Comparator.comparing(
                                SlalomResultDTO::getTotalTime
                        )
                )
                .toList();

        // Assign ranking positions.
        for (int i = 0; i < ranking.size(); i++) {
            ranking.get(i).setRank(i + 1);
        }

        return ranking;
    }

    // ==========================================
    // HELPER METHODS
    // ==========================================

    private SlalomCompetition getSlalomCompetition(Long competitionId) {

        Competition competition = competitionRepository
                .findById(competitionId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Competition not found: " + competitionId
                        )
                );

        if (!(competition instanceof SlalomCompetition slalomCompetition)) {
            throw new IllegalArgumentException(
                    "Competition is not a Slalom competition."
            );
        }

        return slalomCompetition;
    }

    private Athlete getAthlete(Long athleteId) {

        return athleteRepository.findById(athleteId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Athlete not found: " + athleteId
                        )
                );
    }

    private void validateRegistration(
            Long competitionId,
            Long athleteId
    ) {

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
    }

    private void validateDTO(SlalomRunDTO dto) {

        if (dto == null
                || dto.getCompetitionId() == null
                || dto.getAthleteId() == null) {

            throw new IllegalArgumentException(
                    "Competition ID and athlete ID are required."
            );
        }
    }

    private void validateTime(BigDecimal time) {

        if (time == null
                || time.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "Run time must be greater than zero."
            );
        }
    }
}
