package it.esame.shop.repositories;

import it.esame.shop.entities.Carrello;
import it.esame.shop.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {

    Carrello findByUtente(Utente utente);
    //List<Carrello> findByIdcarrello(Utente u);
    List<Carrello> findByDataAcquisto(Date data);

}//CarrelloRepository
