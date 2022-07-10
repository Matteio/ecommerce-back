package it.esame.shop.services;

import it.esame.shop.entities.Acquisto;
import it.esame.shop.entities.ProdottoInAcquisto;
import it.esame.shop.entities.Utente;
import it.esame.shop.repositories.AcquistoRepository;
import it.esame.shop.repositories.ProdottoInAcquistoRepository;
import it.esame.shop.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AcquistoService {

    @Autowired
    private AcquistoRepository acquistoRepository;

    public List<Acquisto> getAll(){
        return acquistoRepository.findAll();
    }

    public void setAcquistoDone(Integer id,Boolean done){
        Acquisto p=acquistoRepository.findByIdacquisto(id);
        p.setDone(done);
        acquistoRepository.flush();
        return;
    }

}
