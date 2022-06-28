package it.esame.shop.controller;

import it.esame.shop.entities.Prodotto;
import it.esame.shop.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prodotti")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping
    public ResponseEntity getProdotti(){
        try{

        }catch (){

        }
    }//getProdotti

    @PostMapping("/add")
    public ResponseEntity aggiungiProdotto(@RequestBody Prodotto prodotto){
        try{

        }catch (){

        }
    }//aggiungiProdotto

    @PutMapping("/edit")
    public ResponseEntity modificaProdotto(@RequestBody Prodotto prodotto){
        try{

        }catch (){

        }
    }//modificaProdotto

    @DeleteMapping("/delete/{id}")
    public ResponseEntity eliminaProdotto(@PathVariable int id){
        try{

        }catch (){

        }
    }//eliminaProdotto

}//ProdottoController
