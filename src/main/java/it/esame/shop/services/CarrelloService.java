package it.esame.shop.services;

import it.esame.shop.entities.*;
import it.esame.shop.repositories.ProdottoInAcquistoRepository;
import it.esame.shop.repositories.CarrelloRepository;
import it.esame.shop.repositories.UtenteRepository;
import it.esame.shop.support.exceptions.QuantityProductUnavailableException;
import it.esame.shop.support.exceptions.UserNotFoundException;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CarrelloService {

    @Autowired
    private CarrelloRepository carrelloRepository;
    @Autowired
    private ProdottoInAcquistoRepository prodottoInAcquistoRepository;
    @Autowired
    private UtenteRepository utenteRepository;


    //aggiungere i lock ottimistici
    @Transactional
    public Carrello checkOut(Carrello carrello) throws QuantityProductUnavailableException{
        Carrello ret= carrelloRepository.save(carrello);
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
    public Carrello addToCarrello (ProdottoInAcquisto p, Utente u) {
        Carrello carrello = carrelloRepository.findByUtente(u);
        Boolean prodInCart=false;
        if(carrello!=null){
            List<ProdottoInAcquisto> prodotti=carrello.getProdottoInAcquisto();
            for(ProdottoInAcquisto pr: prodotti){
                if(pr.getProdotto().equals(p)){
                    prodInCart=true;
                    pr.setQuantita(pr.getQuantita()+p.getQuantita());
                    carrello.setProdottoInAcquisto(prodotti);
                    return carrelloRepository.saveAndFlush(carrello);
                }
            }
        }
        if(!prodInCart && (carrello!=null)){
            carrello.getProdottoInAcquisto().add(p);
            return carrelloRepository.saveAndFlush(carrello);
        }
        else{
            Carrello carrello1=new Carrello();
            carrello1.getProdottoInAcquisto().add(p);
            return carrelloRepository.save(carrello1);
        }
    }//addToCarrello

    @Transactional
    public Carrello  removeFromCart(Prodotto p, Utente u){
        Carrello carrello=carrelloRepository.findByUtente(u);
        List<ProdottoInAcquisto> prodotti=carrello.getProdottoInAcquisto();
        ProdottoInAcquisto daEl=null;
        for(ProdottoInAcquisto pr: prodotti){
            if(pr.getProdotto().equals(p)){
                daEl=pr;
            }
        }
        prodotti.remove(daEl);
        prodottoInAcquistoRepository.delete(daEl);
        carrello.setProdottoInAcquisto(prodotti);
        return carrelloRepository.save(carrello);
    }//removeFromCart

    @Transactional
    public void pulisciCarrello(Utente u){
        Carrello carrello=carrelloRepository.findByUtente(u);
        carrelloRepository.delete(carrello);
    }//pulisci

    @Transactional
    public void eliminaOrdine(int id) {
        carrelloRepository.deleteById(id);
    }//rimiuoviOrdine

    /*@Transactional(readOnly = true)
    public List<Carrello> ricercaPerUtente(Utente utente) throws UserNotFoundException {
        if(!utenteRepository.existsByCf(utente.getCf()))
            throw new UserNotFoundException();
        return carrelloRepository.findByUtente(utente);
    }//getByUtente*/

    @Transactional(readOnly = true)
    public List<Carrello> ricercaPerData(Date d){
        return carrelloRepository.findByDataAcquisto(d);
    }//ricercaPerData

}//CarrelloService
