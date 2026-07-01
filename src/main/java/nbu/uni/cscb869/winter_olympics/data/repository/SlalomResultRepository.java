package nbu.uni.cscb869.winter_olympics.data.repository;

import nbu.uni.cscb869.winter_olympics.data.entity.SlalomResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SlalomResultRepository extends JpaRepository<SlalomResult, Long> {
    List<SlalomResult> findByCompetitionId(Long competitionId);

    Optional<SlalomResult> findByCompetitionIdAndAthleteId(Long competitionId, Long athleteId);
}
