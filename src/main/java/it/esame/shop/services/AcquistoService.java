/*package it.esame.shop.services;

import it.esame.shop.entities.Acquisto;
import it.esame.shop.entities.Carrello;
import it.esame.shop.entities.Utente;
import it.esame.shop.repositories.AcquistoRepository;
import it.esame.shop.repositories.UtenteRepository;
import it.esame.shop.support.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AcquistoService {

    @Autowired
    private AcquistoRepository acquistoRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional
    public void eliminaOrdine(int id) {
        acquistoRepository.deleteByIdacquisto(id);
    }//rimiuoviOrdine

    @Transactional(readOnly = true)
    public List<Acquisto> ricercaPerUtente(Utente utente) throws UserNotFoundException {
        if(!utenteRepository.existsByCf(utente.getCf()))
            throw new UserNotFoundException();
        return acquistoRepository.findByUtente(utente);
    }//getByUtente

    @Transactional(readOnly = true)
    public List<Acquisto> ricercaPerData(Date d){
        return acquistoRepository.findByDataAcquisto(d);
    }//ricercaPerData


}*/
