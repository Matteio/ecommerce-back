
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/prodotti")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @GetMapping("/getAll/paged")
    public ResponseEntity getAll(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                 @RequestParam(value = "sortBy", defaultValue = "id") String sortBy){
        List<Prodotto> result = prodottoService.mostraListaProdotti(pageNumber,pageSize,sortBy);
        if(result.size()<=0){
            return new ResponseEntity<>(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }//getAll paged

    @GetMapping("/getByName")
    public ResponseEntity getByNome (@RequestParam(value="name", required = false)String nome){
        List<Prodotto> result=prodottoService.mostraProdottiByNome(nome);
        if(result.size()<=0)
            return new ResponseEntity<>(new ResponseMessage("Nessun risultato!"), HttpStatus.OK);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }//getByNome

    /*@GetMapping("/getAll")
    public List<Prodotto> getAll(){
        System.out.println("getAll");
        return prodottoService.mostraTutti();
    }//getAll*/

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        List<Prodotto> result = prodottoService.mostraTutti();
        return (result.size()<=0)?
                new ResponseEntity("Nessun Risultato!", HttpStatus.OK):
                new ResponseEntity(result, HttpStatus.OK);
    }

    //@PreAuthorize("hasAuthority('admin')")
    @PostMapping("/admin/add")
    public ResponseEntity aggiungiProdotto(@RequestBody Prodotto prodotto){
        try{
            Prodotto prodAgg = prodottoService.aggiungiProdotto(prodotto);
            return new ResponseEntity<>(new ResponseMessage("Prodotto aggiunto correttamente"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("ADD ERROR", HttpStatus.BAD_REQUEST);
        }
    }//aggiungiProdotto

    //@PreAuthorize("hasAuthority('admin')")
    @PutMapping("/admin/edit")
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

    //@PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity eliminaProdotto(@PathVariable int id){
        try{
            prodottoService.eliminaProdotto(id);
            return new ResponseEntity<>(new ResponseMessage("Prodotto eliminato con successo"), HttpStatus.OK);
        }catch (ProductNotFoundException p){
            return new ResponseEntity<>("DELETE ERROR", HttpStatus.BAD_REQUEST);
        }
    }//eliminaProdotto

}//ProdottoController
