package it.esame.shop.controller;

import it.esame.shop.entities.Utente;
import it.esame.shop.services.ProfiloService;
import it.esame.shop.support.ResponseMessage;
import it.esame.shop.support.exceptions.MailUserAlreadyExistsException;
import it.esame.shop.support.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class ProfiloController {

    @Autowired
    private ProfiloService profiloService;

    @PostMapping("/crea")
    public ResponseEntity crea(@RequestBody Utente utente){
        try{
            Utente daAgg = profiloService.registraUtente(utente);
            return new ResponseEntity(daAgg, HttpStatus.OK);
        }catch(MailUserAlreadyExistsException m){
            return new ResponseEntity<>(new ResponseMessage("ERROR_MAIL_ALREADY_EXISTS"), HttpStatus.BAD_REQUEST);
        }
    }//crea

    @PostMapping("/accedi")
    public ResponseEntity login(@RequestBody Utente utente){
        try{
            Utente utente1=profiloService.login(utente);
            return new ResponseEntity(utente1, HttpStatus.OK);
        }catch (UserNotFoundException u){
            return new ResponseEntity<>(new ResponseMessage("ERROR_USER_NOT_FOUND"), HttpStatus.BAD_REQUEST);
        }
    }//login


    @GetMapping("/get")
    public List<Utente> getAll(){
        return profiloService.getAllUtenti();
    }//getAll

    @PostMapping("/addToCart")
    public ResponseEntity addToCart(@RequestParam String email,
                                    @RequestParam String nomeProdotto,
                                    @RequestParam String quantita){
        try{
            return new ResponseEntity(profiloService.addToCart(email,nomeProdotto,quantita),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Errore nell'aggiunta al carrello",HttpStatus.BAD_REQUEST);
        }
    }//addToCart

    @PostMapping("/setQuantityToCart")
    public ResponseEntity setQuantityToCart(@RequestParam String email,
                                            @RequestParam String nomeProdotto,
                                            @RequestParam String quantita){
        try{
            return new ResponseEntity(profiloService.setQuantityToCart(email, nomeProdotto, quantita),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Errore nel settare la quantita nel carrello",HttpStatus.BAD_REQUEST);
        }
    }//setQuantityToCart

    @PostMapping("/removeFromCart")
    public ResponseEntity removeFromCart(String email,String nomeProdotto){
        try{
            return new ResponseEntity(profiloService.removeFromCart(email, nomeProdotto),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Errore nella rimozione dal carrello",HttpStatus.BAD_REQUEST);
        }
    }//removeFromCart

    @GetMapping("/getCart")
    public ResponseEntity getUserCart(String email){
        try{
            return new ResponseEntity(profiloService.getUserCart(email),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Errore nella restituzione del carrello",HttpStatus.BAD_REQUEST);
        }
    }//getUserCart

}//ProfiloController
