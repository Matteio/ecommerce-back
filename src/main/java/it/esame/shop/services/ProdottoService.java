package it.esame.shop.services;

import it.esame.shop.entities.Prodotto;
import it.esame.shop.repositories.ProdottoRepository;
import it.esame.shop.support.exceptions.BarCodeAlreadyExistException;
import it.esame.shop.support.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;

    @Transactional(readOnly = false)
    public void aggiungiProdotto(Prodotto prodotto) throws BarCodeAlreadyExistException{
        if(prodotto.getBarCode() != null &&
        prodottoRepository.existsByBarCode(prodotto.getBarCode())){
            throw new BarCodeAlreadyExistException();
        }
        prodottoRepository.save(prodotto);
    }//aggiungiProdotto


    //Dato un prodotto esistente, ne carico un altro uguale con dati differenti
    @Transactional(readOnly = false)
    public void modificaProdotto(Prodotto prodotto){
        boolean esiste=prodottoRepository.existsById(prodotto.getIdProdotto());
        if(esiste){
            prodottoRepository.save(prodotto);
        }
    }

    @Transactional(readOnly = false)
    public void eliminaProdotto(Prodotto prodotto) throws ProductNotFoundException {
        if(!prodottoRepository.existsById(prodotto.getIdProdotto())){
            throw new ProductNotFoundException();
        }
        prodottoRepository.deleteById(prodotto.getIdProdotto());
    }

    @Transactional(readOnly = true)
    public Page<Prodotto> mostraListaProdotti(int pag, int limite){
        Pageable pageable = PageRequest.of(pag,limite);
        return prodottoRepository.findAll(pageable);
    }//mostraListaProdotti

    @Transactional(readOnly = true)
    public Page<Prodotto> mostraProdottiByNome(int pag, int limite, String nome){
        Pageable pageable = PageRequest.of(pag,limite);
        return prodottoRepository.findByNameContaining(nome,pageable);
    }//mostraListaProdotti

    @Transactional(readOnly = true)
    public Page<Prodotto> mostraProdottoOrdinatiByPrezzo(int pag, int limite, Sort.Direction sort){
        Sort sort1=Sort.by(sort, "prezzo");
        Pageable pageable=PageRequest.of(pag,limite,sort1);
        return prodottoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Prodotto> mostraProdottoPerNomeOrdinatiByPrezzo(int pag, int limite, String nome, Sort.Direction sort){
        Sort sort1=Sort.by(sort, "prezzo");
        Pageable pageable=PageRequest.of(pag,limite,sort1);
        return prodottoRepository.findByNameContaining(nome,pageable);
    }


}//ProdottoService
