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

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepository;


    @Transactional(readOnly = false)
    public Prodotto aggiungiProdotto(Prodotto prodotto) throws BarCodeAlreadyExistException{
        if(prodotto.getBarCode() != null &&
        prodottoRepository.existsByBarCode(prodotto.getBarCode())){
            throw new BarCodeAlreadyExistException();
        }
        return prodottoRepository.save(prodotto);
    }//aggiungiProdotto


    //Dato un prodotto esistente, ne carico un altro uguale con dati differenti
    @Transactional(readOnly = false)
    public Prodotto modificaProdotto(Prodotto prodotto){
        boolean esiste=prodottoRepository.existsById(prodotto.getIdProdotto());
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
    public Page<Prodotto> getFiltri(int pag,int limite,String nomeProd, Sort.Direction sort) {
        Page<Prodotto> productPage = null;
        if(nomeProd==null && sort==null){
            productPage = mostraListaProdotti(pag,limite);
        }
        if(nomeProd!=null && sort==null ){
            productPage = mostraProdottiByNome(pag,limite,nomeProd);
        }
        if(nomeProd==null && sort != null ){
            productPage = mostraProdottoOrdinatiByPrezzo(pag,limite,sort);
        }
        if(nomeProd!=null && sort!=null){
            productPage = mostraProdottoPerNomeOrdinatiByPrezzo(pag,limite,nomeProd,sort);
        }
        return  productPage;
    }


    @Transactional(readOnly = true)
    public Page<Prodotto> mostraListaProdotti(int pag, int limite){
        Pageable pageable = PageRequest.of(pag,limite);
        return prodottoRepository.findAll(pageable);
    }//mostraListaProdotti

    @Transactional(readOnly = true)
    public Page<Prodotto> mostraProdottiByNome(int pag, int limite, String nome){
        Pageable pageable = PageRequest.of(pag,limite);
        return prodottoRepository.findByNomeContaining(nome,pageable);
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
        return prodottoRepository.findByNomeContaining(nome,pageable);
    }


}//ProdottoService
