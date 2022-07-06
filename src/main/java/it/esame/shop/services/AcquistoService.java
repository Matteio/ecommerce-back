package it.esame.shop.services;

import it.esame.shop.entities.Carrello;
import it.esame.shop.entities.Ordine;
import it.esame.shop.entities.Prodotto;
import it.esame.shop.entities.Utente;
import it.esame.shop.repositories.CarrelloRepository;
import it.esame.shop.repositories.OrdineRepository;
import it.esame.shop.repositories.UtenteRepository;
import it.esame.shop.support.exceptions.QuantityProductUnavailableException;
import it.esame.shop.support.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AcquistoService {
    @Autowired
    private OrdineRepository ordineRepository;
    @Autowired
    private CarrelloRepository carrelloRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional(readOnly = false)
    public Ordine aggiungiAcquisto(Ordine ordine) throws QuantityProductUnavailableException{
        Ordine ret=ordineRepository.save(ordine);
        for(Carrello c : ret.getCarrello()){
            c.setOrdine(ret);
            Carrello aggiunti=carrelloRepository.save(c);
            Prodotto p=aggiunti.getProdotto();
            int nuovaDisponibiilita= p.getDisponibilita()-c.getQuantita();
            if(nuovaDisponibiilita<0)
                throw new QuantityProductUnavailableException();
            p.setDisponibilita(nuovaDisponibiilita);
        }
        return ret;
    }

    @Transactional
    public List<Ordine> getByUtente(Utente utente) throws UserNotFoundException{
        if(!utenteRepository.existsById(utente.getIdutente()))
            throw new UserNotFoundException();
        return ordineRepository.findByUtente(utente);
    }

    @Transactional
    public List<Ordine> getAll(){
        return ordineRepository.findAll();
    }



}//AcquistoService
