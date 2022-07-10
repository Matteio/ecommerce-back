package it.esame.shop.services;

import it.esame.shop.entities.Acquisto;
import it.esame.shop.entities.Prodotto;
import it.esame.shop.entities.ProdottoInAcquisto;
import it.esame.shop.entities.Utente;
import it.esame.shop.repositories.AcquistoRepository;
import it.esame.shop.repositories.ProdottoInAcquistoRepository;
import it.esame.shop.repositories.ProdottoRepository;
import it.esame.shop.repositories.UtenteRepository;
import it.esame.shop.support.exceptions.MailUserAlreadyExistsException;
import it.esame.shop.support.exceptions.ProductNotFoundException;
import it.esame.shop.support.exceptions.QuantityProductUnavailableException;
import it.esame.shop.support.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service @Transactional @RequiredArgsConstructor
public class ProfiloService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private ProdottoInAcquistoRepository prodottoInAcquistoRepository;
    @Autowired
    private AcquistoRepository acquistoRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Utente registraUtente(Utente utente) throws MailUserAlreadyExistsException {
        if(utenteRepository.existsByCf(utente.getCf()))
            throw new MailUserAlreadyExistsException();
        return utenteRepository.save(utente);
    }//registraUtente

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Utente login(Utente utente) throws UserNotFoundException{
        if(!utenteRepository.existsByCf(utente.getCf()))
            throw new UserNotFoundException();
        return utenteRepository.findByCf(utente.getCf());
    }//login

    @Transactional(readOnly = true)
    public List<Utente> getAllUtenti(){
        return utenteRepository.findAll();
    }//getAllUtenti

    public List<Acquisto> getMyOrdini(String email){
        Utente utente=utenteRepository.findByEmail(email);
        return acquistoRepository.findByCompratore(utente);
    }//getMyOrdini

    @Transactional()
    public List<ProdottoInAcquisto> addToCart(String email, String nomeProdotto, String quantita){
        int qta=Integer.parseInt(quantita);
        Prodotto prod=prodottoRepository.findByNome(nomeProdotto);
        if(prod==null)
            throw new ProductNotFoundException();
        Utente utente=utenteRepository.findByEmail(email);
        if(utente==null)
            throw new UserNotFoundException();
        ProdottoInAcquisto pia=prodottoInAcquistoRepository.findByCompratoreAndProdotto(utente,prod);
        if(pia==null){
            if(qta>prod.getDisponibilita())
                throw new QuantityProductUnavailableException();
            pia=new ProdottoInAcquisto(null,qta,prod,utente);
            prodottoInAcquistoRepository.save(pia);
            utente.getCarrello().add(pia);
        }
        else{
            int totale=pia.getQuantita()+qta;
            if(totale> prod.getDisponibilita())
                throw new QuantityProductUnavailableException();
            pia.setQuantita(pia.getQuantita()+qta);
        }
        return utente.getCarrello();
    }//addToCart

    public List<ProdottoInAcquisto> setQuantityToCart(String email, String nomeProdotto, String quantita){
        int qta=Integer.parseInt(quantita);
        Prodotto prod=prodottoRepository.findByNome(nomeProdotto);
        if(prod==null)
            throw new ProductNotFoundException();
        Utente utente=utenteRepository.findByEmail(email);
        if(utente==null)
            throw new UserNotFoundException();
        ProdottoInAcquisto pia=prodottoInAcquistoRepository.findByCompratoreAndProdotto(utente,prod);
        if(pia==null){
            if(qta> prod.getDisponibilita())
                throw new QuantityProductUnavailableException();
            pia=new ProdottoInAcquisto(null,qta,prod,utente);
            prodottoInAcquistoRepository.save(pia);
            utente.getCarrello().add(pia);
        }
        else{
            if(qta> prod.getDisponibilita() && qta>pia.getQuantita())
                throw new QuantityProductUnavailableException();
            pia.setQuantita(qta);
        }
        return utente.getCarrello();
    }//setQuantityToCart

    public List<ProdottoInAcquisto> removeFromCart(String email, String nomeProdotto){
        Utente utente=utenteRepository.findByEmail(email);
        Prodotto prodotto=prodottoRepository.findByNome(nomeProdotto);
        ProdottoInAcquisto pia=prodottoInAcquistoRepository.findByCompratoreAndProdotto(utente,prodotto);
        if(pia==null)
            throw new ProductNotFoundException();
        prodottoInAcquistoRepository.delete(pia);
        return utente.getCarrello();
    }//removeFromCart

    public List<ProdottoInAcquisto> getUserCart(String email){
        Utente utente=utenteRepository.findByEmail(email);
        List<ProdottoInAcquisto> carrello=utente.getCarrello();
        //Collections.sort(carrello);
        return carrello;
    }//getUserCart

}
