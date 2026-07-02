package nbu.uni.cscb869.winter_olympics.data.repository;

import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    List<Athlete> findByCountry(String country);

    List<Athlete> findByGender(Gender gender);

    List<Athlete> findByBirthDateBefore(LocalDate date);
}
