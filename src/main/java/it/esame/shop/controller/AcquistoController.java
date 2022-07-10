package it.esame.shop.controller;

import it.esame.shop.entities.Acquisto;
import it.esame.shop.services.AcquistoService;
import it.esame.shop.services.ProfiloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acquisto")
public class AcquistoController {

    @Autowired
    ProfiloService profiloService;
    @Autowired
    AcquistoService acquistoService;

    @GetMapping("/getAcquisti")
    public ResponseEntity getAcquisti(String email){
        try{
            return new ResponseEntity(profiloService.getMyOrdini(email),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Errore", HttpStatus.BAD_REQUEST);
        }
    }//getAcquisti

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        try{
            return new ResponseEntity(acquistoService.getAll(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Errore", HttpStatus.BAD_REQUEST);
        }
    }//getAll

    @GetMapping("/setAcquistoDone")
    public ResponseEntity setAcquistoDone(@RequestParam String id, @RequestParam String done){
        try{
            acquistoService.setAcquistoDone(Integer.parseInt(id),Boolean.parseBoolean(done));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity("Errore",HttpStatus.BAD_REQUEST);
        }
    }//setAcquistoDone

}

