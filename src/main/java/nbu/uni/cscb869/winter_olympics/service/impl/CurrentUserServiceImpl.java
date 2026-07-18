package nbu.uni.cscb869.winter_olympics.service.impl;
import org.springframework.security.core.Authentication;
import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.repository.AthleteRepository;
import nbu.uni.cscb869.winter_olympics.service.CurrentUserService;
import org.springframework.security.oauth2.jwt.Jwt;

public class CurrentUserServiceImpl implements CurrentUserService {
    private final AthleteRepository athleteRepository;

    public CurrentUserServiceImpl(AthleteRepository athleteRepository) {
        this.athleteRepository = athleteRepository;
    }


    @Override
    public Athlete getCurrentAthlete(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        assert jwt != null;
        String keycloakId = jwt.getSubject();

        return athleteRepository
                .findByKeycloakId(keycloakId)
                .orElseThrow();
    }
}
