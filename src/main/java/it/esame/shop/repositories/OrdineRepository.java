package it.esame.shop.repositories;

import it.esame.shop.entities.Ordine;
import it.esame.shop.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

    List<Ordine> findByUtente(Utente utente);
    List<Ordine> findByDataAcquisto(Date data);

}//OrdineRepository
