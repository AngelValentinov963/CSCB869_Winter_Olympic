package nbu.uni.cscb869.winter_olympics.service;

import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface CurrentUserService {
    public Athlete getCurrentAthlete(Authentication authentication);

}
