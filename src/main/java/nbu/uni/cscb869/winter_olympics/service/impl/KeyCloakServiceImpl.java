package nbu.uni.cscb869.winter_olympics.service.impl;

import lombok.RequiredArgsConstructor;
import nbu.uni.cscb869.winter_olympics.dto.RegisterUserDTO;
import nbu.uni.cscb869.winter_olympics.service.KeyCloakService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeyCloakServiceImpl implements KeyCloakService {

    private final Keycloak keycloak;

    private static final String REALM = "winter-olympics-application-realm";

    @Override
    public void createUser(RegisterUserDTO dto) {

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(dto.getPassword());
        credential.setTemporary(false);

        UserRepresentation user = new UserRepresentation();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setEnabled(true);
        user.setCredentials(List.of(credential));

        keycloak.realm(REALM)
                .users()
                .create(user);
    }

    @Override
    public void deleteUser(String keycloakUserId) {

        keycloak.realm(REALM)
                .users()
                .delete(keycloakUserId);
    }

    @Override
    public void assignRole(String keycloakUserId, String roleName) {

        RealmResource realm = keycloak.realm(REALM);

        RoleRepresentation role =
                realm.roles()
                        .get(roleName)
                        .toRepresentation();

        realm.users()
                .get(keycloakUserId)
                .roles()
                .realmLevel()
                .add(List.of(role));
    }
}