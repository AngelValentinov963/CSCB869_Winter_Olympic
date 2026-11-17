
package nbu.uni.cscb869.winter_olympics.data.repository;

import nbu.uni.cscb869.winter_olympics.data.entity.Result.BiathlonResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BiathlonResultRepository extends JpaRepository<BiathlonResult, Long> {

    Optional<BiathlonResult> findByAthleteId(Long athleteId);

    Optional<BiathlonResult> findByCompetitionIdAndAthleteId(
            Long competitionId,
            Long athleteId
    );

    List<BiathlonResult> findByCompetitionId(Long competitionId);
}
