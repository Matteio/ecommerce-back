package it.esame.shop.services;

import it.esame.shop.entities.ProdottoInAcquisto;
import it.esame.shop.entities.Ordine;
import it.esame.shop.entities.Prodotto;
import it.esame.shop.entities.Utente;
import it.esame.shop.repositories.ProdottoInAcquistoRepository;
import it.esame.shop.repositories.OrdineRepository;
import it.esame.shop.repositories.UtenteRepository;
import it.esame.shop.support.exceptions.QuantityProductUnavailableException;
import it.esame.shop.support.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;
    @Autowired
    private ProdottoInAcquistoRepository prodottoInAcquistoRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional()
    public Ordine aggiungiAcquisto(Ordine ordine) throws QuantityProductUnavailableException{
        Ordine ret=ordineRepository.save(ordine);
        for(ProdottoInAcquisto c : ret.getProdottoInAcquisto()){
            c.setOrdine(ret);
            ProdottoInAcquisto aggiunti= prodottoInAcquistoRepository.save(c);
            Prodotto p=aggiunti.getProdotto();
            int nuovaDisponibiilita = p.getDisponibilita()-c.getQuantita();
            if(nuovaDisponibiilita<0)
                throw new QuantityProductUnavailableException();
            p.setDisponibilita(nuovaDisponibiilita);
        }
        return ret;
    }//aggiungiAcquisto

    @Transactional
    public void eliminaOrdine(int id) {
        ordineRepository.deleteById(id);
    }//rimiuoviOrdine

    @Transactional(readOnly = true)
    public List<Ordine> ricercaPerUtente(Utente utente) throws UserNotFoundException{
        if(!utenteRepository.existsByCf(utente.getCf()))
            throw new UserNotFoundException();
        return ordineRepository.findByUtente(utente);
    }//getByUtente

    @Transactional(readOnly = true)
    public List<Ordine> ricercaPerData(Date d){
        return ordineRepository.findByDataAcquisto(d);
    }//ricercaPerData

}//OrdineService
