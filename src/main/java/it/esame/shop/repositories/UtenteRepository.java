package it.esame.shop.repositories;

import it.esame.shop.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

    List<Utente> findByNome(String nome);
    List<Utente> findByCognome(String cognome);
    //List<Utente> findByNomeANDCognome(String nome, String cognome);
    Utente findByCf(String cf);
    Utente findByEmail(String email);
    boolean existsByCf(String cf);
    boolean existsByEmail(String email);

}//UtenteRepository
