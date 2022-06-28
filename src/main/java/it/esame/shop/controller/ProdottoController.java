package it.esame.shop.controller;

import it.esame.shop.entities.Prodotto;
import it.esame.shop.services.ProdottoService;
import it.esame.shop.support.ResponseMessage;
import it.esame.shop.support.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prodotti")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping
    public ResponseEntity getProdotti(@RequestParam(required = false,defaultValue = "0") int pag,
                                      @RequestParam(required = false,defaultValue = "7") int limite,
                                      @RequestParam(required = false)  String nomeProd,
                                      @RequestParam(required = false) Sort.Direction sort){
        try{
            Page<Prodotto> prodottoPage = prodottoService.getFiltri(pag,limite,nomeProd,sort);
            return new ResponseEntity<>(new ResponseMessage("Prodotti restituiti correttamente"),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("GET ERROR", HttpStatus.BAD_REQUEST);
        }
    }//getProdotti

    @PostMapping("/add")
    public ResponseEntity aggiungiProdotto(@RequestBody Prodotto prodotto){
        try{
            Prodotto prodAgg = prodottoService.aggiungiProdotto(prodotto);
            return new ResponseEntity<>(new ResponseMessage("Prodotto aggiunto correttamente"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("ADD ERROR", HttpStatus.BAD_REQUEST);
        }
    }//aggiungiProdotto

    @PutMapping("/edit")
    public ResponseEntity modificaProdotto(@RequestBody Prodotto prodotto){
        try{
            Prodotto prodMod = prodottoService.modificaProdotto(prodotto);
            if(prodMod!=null){
                return new ResponseEntity<>(new ResponseMessage("Prodotto modificato correttamente"), HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Il prodotto non esiste", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("MODIFY ERROR", HttpStatus.BAD_REQUEST);
        }
    }//modificaProdotto

    @DeleteMapping("/delete/{id}")
    public ResponseEntity eliminaProdotto(@PathVariable int id){
        try{
            prodottoService.eliminaProdotto(id);
            return new ResponseEntity<>(new ResponseMessage("Prodotto eliminato con successo"), HttpStatus.OK);
        }catch (ProductNotFoundException p){
            return new ResponseEntity<>("DELETE ERROR", HttpStatus.BAD_REQUEST);
        }
    }//eliminaProdotto

}//ProdottoController
