/*package it.esame.shop.services;

import it.esame.shop.entities.Prodotto;
import it.esame.shop.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShoppingCartService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    private Map<Prodotto, Integer> prodotti = new HashMap<>();

    @Transactional
    public void addProduct(Prodotto prodotto){
        if(prodotti.containsKey(prodotto)){
            prodotti.replace(prodotto,prodotti.get(prodotto)+1);
        }else{
            prodotti.put(prodotto,1);
        }
    }//addProduct

    @Transactional
    public void removeProduct(Prodotto prodotto){
        if(prodotti.containsKey(prodotto)){
            if(prodotti.get(prodotto)>1)
                prodotti.replace(prodotto,prodotti.get(prodotto)-1);
            else if(prodotti.get(prodotto)==1){
                prodotti.remove(prodotto);
            }
        }
    }//removeProdotto

    @Transactional(readOnly = true)
    public Map<Prodotto,Integer> getProdottiInCart(){
        return Collections.unmodifiableMap(prodotti);
    }//getProdottiInCart


}//ShoppingCartService*/
