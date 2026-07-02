package nbu.uni.cscb869.winter_olympics.service;

import jakarta.validation.Valid;
import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.repository.AthleteRepository;
import nbu.uni.cscb869.winter_olympics.dto.AthleteCreateDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
public interface AthleteService {
    void registerAthlete(AthleteCreateDTO athleteCreateDTO);
    Athlete create(AthleteCreateDTO athlete);
    Athlete update(Long id , Athlete athlete);
    void delete(Long id);
    Athlete findById(Long id);
    List<Athlete> findAll();
    List<Athlete> findByCountry(String country);
}
