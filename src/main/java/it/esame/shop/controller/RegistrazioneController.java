
package it.esame.shop.controller;

import it.esame.shop.entities.Utente;
import it.esame.shop.services.RegistrazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
public class RegistrazioneController {

    @Autowired
    RegistrazioneService registrazioneService;

    @PostMapping("/r")
    public @ResponseBody ResponseEntity registrazione(@RequestBody User user){
        String pass[]={user.password};
        String users[]={user.username};
        String mails[]={user.email};
        System.out.println("pass[]: "+ Arrays.toString(pass));
        System.out.println("mails[]: "+ Arrays.toString(mails));
        System.out.println("users[]: "+ Arrays.toString(users));


        try{
            System.out.println("Chiamo registra");
            registrazioneService.registra(pass,users,mails,user.utente);
        }catch (Exception e){
            System.out.println("RCODIO");
            return new ResponseEntity("Errore di registrazione", HttpStatus.BAD_REQUEST);
        }
        System.out.println("registrazione completata");
        return new ResponseEntity("Registrazione completata", HttpStatus.OK);
    }//registrazione

    private static class User{
        Utente utente;
        String email;
        String username;
        String password;

        /*public Utente getUtente() {
            return utente;
        }*/

        public void setUtente(Utente utente) {
            this.utente = utente;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}//RegistrazioneController


