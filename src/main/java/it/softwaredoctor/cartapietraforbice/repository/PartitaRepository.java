package it.softwaredoctor.cartapietraforbice.repository;

import it.softwaredoctor.cartapietraforbice.model.Partita;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartitaRepository extends JpaRepository<Partita, Long> {
    Optional <Partita> findByCodPartita(String codPartita);
}
