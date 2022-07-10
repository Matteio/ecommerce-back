package it.esame.shop.controller;

import it.esame.shop.entities.Carrello;
import it.esame.shop.entities.Utente;
import it.esame.shop.services.CarrelloService;
import it.esame.shop.support.exceptions.QuantityProductUnavailableException;
import it.esame.shop.support.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ordini")
public class OrdineController {

    @Autowired
    private CarrelloService carrelloService;
    //private AcquistoService acquistoService;

    @PostMapping("/addOrdine")
    @ResponseStatus(code= HttpStatus.OK)
    //@PreAuthorize("hasAuthority('Cliente')")
    public ResponseEntity addOrdine(@RequestBody Carrello carrello){
        try{
            return new ResponseEntity<>(carrelloService.checkOut(carrello), HttpStatus.OK);
        }catch(QuantityProductUnavailableException q){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }//crea

    /*@GetMapping("/get/{utente}")
    public List<Carrello> getOrdini(@PathVariable Utente utente){
        try{
            return carrelloService.ricercaPerUtente(utente);
        }catch (UserNotFoundException u){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found", u);
        }
    }*/

    @GetMapping("/delete/{id}")
    public  ResponseEntity eliminaOrdine(@PathVariable int id){
        carrelloService.eliminaOrdine(id);
        return new ResponseEntity("Eliminazione effettuata", HttpStatus.OK);
    }

    @GetMapping("/perData")
    public  ResponseEntity ricercaPerData(@RequestParam Date d){
        List<Carrello> list= carrelloService.ricercaPerData(d);
        return(list.size()>0)?
                new ResponseEntity(list,HttpStatus.OK):
                new ResponseEntity("Nessun Carrello per questa data", HttpStatus.OK);
    }

}//OrdineController
