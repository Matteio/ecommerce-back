package it.esame.shop.repositories;

import it.esame.shop.entities.ProdottoInAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoInAcquistoRepository extends JpaRepository<ProdottoInAcquisto, Integer> {

}//ProdottoInAcquistoRepository
