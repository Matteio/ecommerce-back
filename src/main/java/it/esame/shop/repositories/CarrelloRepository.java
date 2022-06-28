package it.esame.shop.repositories;

import it.esame.shop.entities.Carrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {

}//CarrelloRepository
