package nbu.uni.cscb869.winter_olympics.service.impl;

import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.entity.Login.User;
import nbu.uni.cscb869.winter_olympics.data.repository.AthleteRepository;
import nbu.uni.cscb869.winter_olympics.dto.AthleteCreateDTO;
import nbu.uni.cscb869.winter_olympics.service.AthleteService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteServiceImpl implements AthleteService {
    private final AthleteRepository athleteRepository;

    public AthleteServiceImpl(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    @Override
    public void registerAthlete(AthleteCreateDTO dto) {

        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Athlete athlete = Athlete.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .country(dto.getCountry())
                .gender(dto.getGender())
                .birthDate(dto.getBirthDate())
                .user(user)
                .build();

        athleteRepository.save(athlete);
    }

    public Athlete create(AthleteCreateDTO dto) {

        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Athlete athlete = Athlete.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .country(dto.getCountry())
                .gender(dto.getGender())
                .birthDate(dto.getBirthDate())
                .user(user)
                .build();

        athleteRepository.save(athlete);
        return athlete;
    }

    @Override
    public Athlete update(Long id, Athlete athlete) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Athlete findById(Long id) {
        return null;
    }

    @Override
    public List<Athlete> findAll() {
        return List.of();
    }

    @Override
    public List<Athlete> findByCountry(String country) {
        return List.of();
    }
}
