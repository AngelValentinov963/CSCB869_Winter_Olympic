
package nbu.uni.cscb869.winter_olympics.data.repository;

import nbu.uni.cscb869.winter_olympics.data.entity.Result.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    Optional<Result> findByCompetitionIdAndAthleteId(
            Long competitionId,
            Long athleteId
    );
}
