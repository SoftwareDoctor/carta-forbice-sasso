package it.softwaredoctor.cartapietraforbice.repository;

import it.softwaredoctor.cartapietraforbice.model.MosseSingolaPartita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MosseSingolaPartitaRepository extends JpaRepository<MosseSingolaPartita, Long> {
}
