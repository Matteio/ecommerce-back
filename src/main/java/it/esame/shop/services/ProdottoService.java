package it.esame.shop.services;

import it.esame.shop.entities.Prodotto;
import it.esame.shop.repositories.ProdottoRepository;
import it.esame.shop.support.exceptions.BarCodeAlreadyExistException;
import it.esame.shop.support.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;


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



}//ProdottoService
