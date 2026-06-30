package nbu.uni.cscb869.winter_olympics.service.impl;

import lombok.AllArgsConstructor;
import nbu.uni.cscb869.winter_olympics.data.repository.UserRepository;
import nbu.uni.cscb869.winter_olympics.service.UserService;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
