package nbu.uni.cscb869.winter_olympics.service.impl;

import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.repository.AthleteRepository;
import nbu.uni.cscb869.winter_olympics.dto.AthleteCreateDTO;
import nbu.uni.cscb869.winter_olympics.service.AthleteService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteServiceImpl implements AthleteService {

    private final AthleteRepository athleteRepository;

    public AthleteServiceImpl(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    @Override
    public Athlete create(AthleteCreateDTO dto, Authentication authentication) {

        Jwt jwt = (Jwt) authentication.getPrincipal();

        String keycloakId = jwt.getSubject();


        Athlete athlete = Athlete.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .country(dto.getCountry())
                .gender(dto.getGender())
                .birthDate(dto.getBirthDate())
                .keycloakId(keycloakId)
                .build();


        return athleteRepository.save(athlete);
    }

    @Override
    public Athlete update(Long id, Athlete athlete) {
        return athleteRepository.save(athlete);
    }


    @Override
    public void delete(Long id) {
        athleteRepository.deleteById(id);
    }


    @Override
    public Athlete findById(Long id) {
        return athleteRepository.findById(id).orElse(null);
    }


    @Override
    public List<Athlete> findAll() {
        return athleteRepository.findAll();
    }


    @Override
    public List<Athlete> findByCountry(String country) {
        return athleteRepository.findByCountry(country);
    }
}