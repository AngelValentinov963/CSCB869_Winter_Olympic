package nbu.uni.cscb869.winter_olympics.service;

import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.dto.AthleteCreateDTO;
import org.springframework.security.core.Authentication;

import java.util.List;
public interface AthleteService {
    //void registerAthlete(AthleteCreateDTO athleteCreateDTO);
    Athlete create(AthleteCreateDTO athlete, Authentication authentication);
    Athlete update(Long id , Athlete athlete);
    void delete(Long id);
    Athlete findById(Long id);
    List<Athlete> findAll();
    List<Athlete> findByCountry(String country);

}
