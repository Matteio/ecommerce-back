package it.esame.shop.controller;

import it.esame.shop.entities.Ordine;
import it.esame.shop.entities.Utente;
import it.esame.shop.services.AcquistoService;
import it.esame.shop.support.exceptions.QuantityProductUnavailableException;
import it.esame.shop.support.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/acquisti")
public class AcquistoController {

    @Autowired
    private AcquistoService acquistoService;

    @PostMapping
    @ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity crea(@RequestBody Ordine ordine){
        try{
            return new ResponseEntity<>(acquistoService.aggiungiAcquisto(ordine), HttpStatus.OK);
        }catch(QuantityProductUnavailableException q){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }//crea

    @GetMapping("/{utente}")
    public List<Ordine> getOrdini(@RequestBody Utente utente){
        try{
            return acquistoService.getByUtente(utente);
        }catch (UserNotFoundException u){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found", u);
        }
    }



}//AcquistoController
