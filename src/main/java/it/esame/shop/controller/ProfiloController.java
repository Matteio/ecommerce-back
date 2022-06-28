package it.esame.shop.controller;

import it.esame.shop.entities.Utente;
import it.esame.shop.services.ProfiloService;
import it.esame.shop.support.ResponseMessage;
import it.esame.shop.support.exceptions.MailUserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class ProfiloController {

    @Autowired
    private ProfiloService profiloService;

    @PostMapping
    public ResponseEntity crea(@RequestBody Utente utente){
        try{
            Utente daAgg = profiloService.registraUtente(utente);
            return new ResponseEntity(daAgg, HttpStatus.OK);
        }catch(MailUserAlreadyExistsException m){
            return new ResponseEntity<>(new ResponseMessage("ERROR_MAIL_ALREADY_EXISTS"), HttpStatus.BAD_REQUEST);
        }
    }//crea

    @GetMapping
    public List<Utente> getAll(){
        return profiloService.getAllUtenti();
    }//getAll

}//ProfiloController
