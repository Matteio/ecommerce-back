/*package it.esame.shop.controller;

import it.esame.shop.entities.Prodotto;
import it.esame.shop.services.ProdottoService;
import it.esame.shop.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    private ProdottoService prodottoService;

    @PostMapping("/addToCart")
    public ResponseEntity addToCart(@PathVariable Prodotto prodotto){
        shoppingCartService.addProduct(prodotto);
        return new ResponseEntity("Prodotto aggiunto al cart", HttpStatus.OK);
    }//addToCart

    @GetMapping("/removeFromCart")
    public ResponseEntity removeFromCart(@PathVariable Prodotto prodotto){
        shoppingCartService.removeProduct(prodotto);
        return new ResponseEntity("Prodotto rimosso dal cart", HttpStatus.OK);
    }//removeFromCart



}//ShoppingCartController*/
