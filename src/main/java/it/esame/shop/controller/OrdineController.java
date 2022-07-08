package it.esame.shop.controller;

import it.esame.shop.entities.Ordine;
import it.esame.shop.entities.Utente;
import it.esame.shop.services.OrdineService;
import it.esame.shop.support.exceptions.QuantityProductUnavailableException;
import it.esame.shop.support.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ordini")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    @PostMapping("/addOrdine")
    @ResponseStatus(code= HttpStatus.OK)
    //@PreAuthorize("hasAuthority('Cliente')")
    public ResponseEntity addOrdine(@RequestBody Ordine ordine){
        try{
            return new ResponseEntity<>(ordineService.aggiungiAcquisto(ordine), HttpStatus.OK);
        }catch(QuantityProductUnavailableException q){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }//crea

    @GetMapping("/get/{utente}")
    public List<Ordine> getOrdini(@PathVariable Utente utente){
        try{
            return ordineService.ricercaPerUtente(utente);
        }catch (UserNotFoundException u){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found", u);
        }
    }

    @GetMapping("/delete/{id}")
    public @ResponseBody ResponseEntity eliminaOrdine(@PathVariable int id){
        ordineService.eliminaOrdine(id);
        return new ResponseEntity("Eliminazione effettuata", HttpStatus.OK);
    }

    @GetMapping("/perData")
    public @ResponseBody ResponseEntity ricercaPerData(@RequestParam Date d){
        List<Ordine> list=ordineService.ricercaPerData(d);
        return(list.size()>0)?
                new ResponseEntity(list,HttpStatus.OK):
                new ResponseEntity("Nessun Ordine per questa data", HttpStatus.OK);
    }


}//OrdineController
