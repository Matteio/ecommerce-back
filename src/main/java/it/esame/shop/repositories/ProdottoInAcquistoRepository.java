package it.esame.shop.repositories;

import it.esame.shop.entities.Prodotto;
import it.esame.shop.entities.ProdottoInAcquisto;
import it.esame.shop.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoInAcquistoRepository extends JpaRepository<ProdottoInAcquisto, Integer> {
    ProdottoInAcquisto findByCompratoreAndProdotto(Utente u, Prodotto p);
    List<ProdottoInAcquisto> findByProdotto(Prodotto p);
}//ProdottoInAcquistoRepository
