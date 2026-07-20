package nbu.uni.cscb869.winter_olympics.service;

import nbu.uni.cscb869.winter_olympics.dto.RegisterUserDTO;

public interface KeyCloakService {
    String createUser(RegisterUserDTO userRegisterDTO);

    void deleteUser(String keycloakUserId);

    void assignRole(String keycloakUserId, String roleName);
}
