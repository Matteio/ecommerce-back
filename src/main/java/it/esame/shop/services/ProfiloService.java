package it.esame.shop.services;

import it.esame.shop.entities.Utente;
import it.esame.shop.repositories.UtenteRepository;
import it.esame.shop.support.exceptions.MailUserAlreadyExistsException;
import it.esame.shop.support.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfiloService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Utente registraUtente(Utente utente) throws MailUserAlreadyExistsException {
        if(utenteRepository.existsByIdutente(utente.getIdutente()))
            throw new MailUserAlreadyExistsException();
        return utenteRepository.save(utente);
    }//registraUtente

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Utente login(Utente utente) throws UserNotFoundException{
        if(!utenteRepository.existsByIdutente(utente.getIdutente()))
            throw new UserNotFoundException();
        return utenteRepository.findByIdutente(utente.getIdutente());
    }//login

    @Transactional(readOnly = true)
    public List<Utente> getAllUtenti(){
        return utenteRepository.findAll();
    }//getAllUtenti

}
