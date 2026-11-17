package nbu.uni.cscb869.winter_olympics.data.repository;

import nbu.uni.cscb869.winter_olympics.data.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipationRepository
        extends JpaRepository<Participation, Long> {

    boolean existsByCompetitionIdAndAthleteId(
            Long competitionId,
            Long athleteId
    );

    Optional<Participation> findByCompetitionIdAndAthleteId(
            Long competitionId,
            Long athleteId
    );
}