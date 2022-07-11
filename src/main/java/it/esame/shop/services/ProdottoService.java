package it.esame.shop.services;

import it.esame.shop.entities.Acquisto;
import it.esame.shop.entities.Prodotto;
import it.esame.shop.entities.ProdottoInAcquisto;
import it.esame.shop.entities.Utente;
import it.esame.shop.repositories.AcquistoRepository;
import it.esame.shop.repositories.ProdottoInAcquistoRepository;
import it.esame.shop.repositories.ProdottoRepository;
import it.esame.shop.repositories.UtenteRepository;
import it.esame.shop.support.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
//import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private AcquistoRepository acquistoRepository;
    @Autowired
    private ProdottoInAcquistoRepository prodottoInAcquistoRepository;


    @Transactional(readOnly = false)
    public Prodotto aggiungiProdotto(Prodotto prodotto) throws BarCodeAlreadyExistException{
        /*if(prodottoRepository.existsById(prodotto.getIdProdotto())){
            throw new BarCodeAlreadyExistException();
        }

         */
        return prodottoRepository.save(prodotto);
    }//aggiungiProdotto


    //Dato un prodotto esistente, ne carico un altro uguale con dati differenti
    @Transactional(readOnly = false)
    public Prodotto modificaProdotto(Prodotto prodotto){
        boolean esiste=prodottoRepository.existsById(prodotto.getIdprodotto());
        if(esiste){
            return prodottoRepository.save(prodotto);
        }
        return null;
    }

    @Transactional(readOnly = false)
    public void eliminaProdotto(int prodotto) throws ProductNotFoundException {
        if(!prodottoRepository.existsById(prodotto)){
            throw new ProductNotFoundException();
        }
        prodottoRepository.deleteById(prodotto);
    }

    @Transactional(readOnly = true)
    public List<Prodotto> mostraTutti(){
        List<Prodotto> ret=prodottoRepository.findAll();
        System.out.print("[ ");
        for(Prodotto p: ret){
            System.out.print(p+",");
        }
        return prodottoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Prodotto> mostraListaProdotti(int pag, int limite, String sortBy){
        Pageable paging = PageRequest.of(pag, limite, Sort.by(sortBy));
        Page<Prodotto> pageResult=prodottoRepository.findAll(paging);
        if(pageResult.hasContent())
            return pageResult.getContent();
        else
            return new ArrayList<>();
    }//mostraListaProdotti

    @Transactional(readOnly = true)
    public List<Prodotto> mostraProdottiByNome(String nome){
        return prodottoRepository.findByNomeContaining(nome);
    }//mostraListaProdotti

    @Transactional()
    public void compraProdotto(Utente utente, String nomeProd,int quantita)throws RuntimeException{
        //int quantita=Integer.parseInt(qta);
        Prodotto p=prodottoRepository.findByNome(nomeProd);
        if(p==null)
            throw new ProductNotFoundException();
        int newQta=p.getDisponibilita()-quantita;
        if(newQta<0)
            throw new QuantityProductUnavailableException();
        //Date myDate = Date.from(Instant.now());
        //SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        //String formattedDate = formatter.format(myDate);
        Acquisto acquisto=new Acquisto(null, null ,utente,p,quantita,false);
        acquistoRepository.save(acquisto);
        p.setDisponibilita(newQta);
        ProdottoInAcquisto temp=prodottoInAcquistoRepository.findByCompratoreAndProdotto(utente,p);
        prodottoInAcquistoRepository.delete(temp);
        prodottoRepository.flush();
    }

    @Transactional()
    public void acquistaCart(@RequestParam String email){
        System.out.println("AcquistaCart");
        Utente utente=utenteRepository.findByEmail(email);
        if(utente==null)
            throw new UserNotFoundException();
        if(utente.getCarrello().size()==0)
            throw new CartIsEmptyException();
        for(ProdottoInAcquisto p: utente.getCarrello()){
            compraProdotto(utente,p.getProdotto().getNome(),p.getQuantita());
        }
    }


}//ProdottoService
