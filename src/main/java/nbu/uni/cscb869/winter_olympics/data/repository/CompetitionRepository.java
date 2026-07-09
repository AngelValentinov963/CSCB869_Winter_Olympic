package nbu.uni.cscb869.winter_olympics.data.repository;

import nbu.uni.cscb869.winter_olympics.data.entity.Competition.Competition;
import nbu.uni.cscb869.winter_olympics.data.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    //List<Competition> findByCountry(String country);
    List<Competition> findAllByAllowedGender(Gender gender);
    //List<Competition> findByMinimumAgeLessThanEqual(int minimumAge);
}
