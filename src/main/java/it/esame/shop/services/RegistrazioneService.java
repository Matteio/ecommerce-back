
package it.esame.shop.services;

import it.esame.shop.entities.Utente;
import it.esame.shop.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;

@Service
public class RegistrazioneService {

    @Autowired
    private UtenteRepository utenteRepository;

    public void registra(String[] pass, String[] users, String[] ems, Utente utente){
        String usernameAdmin="admin";
        String passwordAdmin="admin";
        String clientName="shop-client";
        String role = "admin";
        String[] email=ems;
        String[] password=pass;
        String[] lastName=users;
        String serverUrl="http://localhost:8080/auth";
        String realm="shop";
        String clientID="shop-client";
        String clientSecret="k7xwjrx81N16w3OnkQkq00NepKjlimhM";

        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(clientID)
                .clientSecret(clientSecret)
                .username(usernameAdmin)
                .password(passwordAdmin)
                .build();

        for(int i=0; i< email.length; i++){

            //Definisco l'utente
            UserRepresentation user = new UserRepresentation();
            user.setEnabled(true);
            user.setUsername(lastName[i]);
            user.setEmail(email[i]);

            user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));

            //Get realm
            RealmResource realmResource = keycloak.realm(realm);
            UsersResource usersRessource = realmResource.users();

            //creazione utente
            System.out.println("Creazione utente");
            Response response=usersRessource.create(user);
            System.out.printf("Response: %s %s%n", response.getStatus(), response.getStatusInfo());
            System.out.println("response location"+response.getLocation());
            String userId = CreatedResponseUtil.getCreatedId(response);
            System.out.printf("User created with userId: %s%n", userId);

            // Define password credential
            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(password[i]);

            UserResource userResource = usersRessource.get(userId);

            // Set password credential
            userResource.resetPassword(passwordCred);

            // Get client
            ClientRepresentation app1Client = realmResource.clients().findByClientId(clientName).get(0);

            // Get client level role (requires view-clients role)
            RoleRepresentation userClientRole = realmResource.clients().get(app1Client.getId()).roles().get(role).toRepresentation();

            // Assign client level role to user
            userResource.roles().clientLevel(app1Client.getId()).add(Arrays.asList(userClientRole));

            // Send password reset E-Mail
            // VERIFY_EMAIL, UPDATE_PROFILE, CONFIGURE_TOTP, UPDATE_PASSWORD, TERMS_AND_CONDITIONS
            usersRessource.get(userId).executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));

            System.out.println("Registrato");
            utenteRepository.save(utente);

        }
    }//registra

}//RegistrazioneService


