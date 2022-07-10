package it.esame.shop.repositories;

import it.esame.shop.entities.Prodotto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {

    List<Prodotto> findByNomeContaining(String nome);
    Prodotto findByNome(String nome);

    //List<Prodotto> findByBarCode(String barCode);
    boolean existsByIdprodotto(int id);

}//ProdottoRepository
