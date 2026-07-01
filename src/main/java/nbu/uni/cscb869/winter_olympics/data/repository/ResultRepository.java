package nbu.uni.cscb869.winter_olympics.data.repository;

import nbu.uni.cscb869.winter_olympics.data.entity.Result.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Integer> {
    Optional<Result> findByCompetitionIdAndAthleteId(Long competitionId, Long athleteId);
}
