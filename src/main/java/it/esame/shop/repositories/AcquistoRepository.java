package it.esame.shop.repositories;

import it.esame.shop.entities.Acquisto;
import it.esame.shop.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Integer> {
    List<Acquisto> findByCompratore(Utente u);
    List<Acquisto> findByDone(boolean done);
    Acquisto findByIdacquisto(int id);
}
