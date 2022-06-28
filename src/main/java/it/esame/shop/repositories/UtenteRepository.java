package it.esame.shop.repositories;

import it.esame.shop.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

    List<Utente> findByFirstName(String nome);
    List<Utente> findByLastName(String cognome);
    List<Utente> findBynomeANDcognome(String nome, String cognome);
    List<Utente> findByEmail(String email);
    List<Utente> findByCode(String barC);
    boolean existsByEmail(String email);

}//UtenteRepository
