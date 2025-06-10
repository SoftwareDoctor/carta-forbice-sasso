package it.softwaredoctor.cartapietraforbice.service;

import it.softwaredoctor.cartapietraforbice.dto.PartitaDTO;
import it.softwaredoctor.cartapietraforbice.model.Carta;
import it.softwaredoctor.cartapietraforbice.model.Forbice;
import it.softwaredoctor.cartapietraforbice.model.Mossa;
import it.softwaredoctor.cartapietraforbice.model.MosseSingolaPartita;
import it.softwaredoctor.cartapietraforbice.model.Partita;
import it.softwaredoctor.cartapietraforbice.model.Player;
import it.softwaredoctor.cartapietraforbice.model.Sasso;
import it.softwaredoctor.cartapietraforbice.repository.MosseSingolaPartitaRepository;
import it.softwaredoctor.cartapietraforbice.repository.PartitaRepository;
import it.softwaredoctor.cartapietraforbice.repository.PlayerRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {

    private final PlayerRepository playerRepository;
    private final PartitaRepository partitaRepository;
    private final MosseSingolaPartitaRepository mosseRepository;

    private String createCodPartita(Player player, Long codPartita) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = now.format(formatter);
        String name = player.getName().replaceAll("\\s+", "");
        return name + "-" + formattedDate + "-" + codPartita;
    }

    public void createNewPlayer(String namePlayer) {
        boolean exists = playerRepository.findByName(namePlayer).isPresent();
        if (!exists) {
            Player player = Player.builder()
                    .name(namePlayer)
                    .build();
            playerRepository.save(player);
        }
    }

    public void createNewPartita(Long idPlayer) {
        log.info("Creazione di una nuova partita");
        Partita partita = new Partita();
        Player player = playerRepository.findById(idPlayer)
                .orElseThrow(() -> new IllegalArgumentException("Player not found with id: " + idPlayer));
        partita.setPlayer(player);
        partitaRepository.save(partita);
        partita.setCodPartita(createCodPartita(player, partita.getId()));
        player.getPartiteGiocate().add(partita);
        partitaRepository.save(partita);
    }

    private Mossa generaMossaCasuale(String codPartita) {
        int random = (int) (Math.random() * 3);
        return switch (random) {
            case 0 -> new Carta();
            case 1 -> new Sasso();
            case 2 -> new Forbice();
            default -> throw new IllegalStateException("Unexpected value: " + random);
        };
    }

    public int startGame(String codPartita, String playerMossa) {
        Mossa mossaComputer = generaMossaCasuale(codPartita);
        Partita partita = partitaRepository.findByCodPartita(codPartita)
                .orElseThrow(() -> new IllegalArgumentException("Partita non trovata con codice: " + codPartita));
        Mossa mossaPlayer = generaMossaPlayer(playerMossa);
        partita.setStato("start");
        MosseSingolaPartita mossaComputerSingola = new MosseSingolaPartita(mossaComputer, mossaPlayer);
        mossaComputerSingola.setPartita(partita);
        mossaComputerSingola.setTipoMossaPlayer(mossaPlayer.getClass().getSimpleName().toLowerCase());
        mossaComputerSingola.setTipoMossaComputer(mossaComputer.getClass().getSimpleName().toLowerCase());
        mosseRepository.save(mossaComputerSingola);
        partita.getMosseSingolaPartita().add(mossaComputerSingola);
        partita.setStato("end");
        partitaRepository.save(partita);
        return confrontaMossa(mossaComputer, mossaPlayer, codPartita);
    }

    private Mossa generaMossaPlayer(String tipoMossa) {
        return switch (tipoMossa.toLowerCase()) {
            case "carta" -> new Carta();
            case "sasso" -> new Sasso();
            case "forbice" -> new Forbice();
            default -> throw new IllegalArgumentException("Tipo di mossa non valido: " + tipoMossa);
        };
    }

    private int confrontaMossa(Mossa mossa1, Mossa mossa2, String codPartita) {
        int risultato = mossa1.confrontaMossa(mossa2);
        Partita partita = partitaRepository.findByCodPartita(codPartita)
                .orElseThrow(() -> new IllegalArgumentException("Partita non trovata con codice: " + codPartita));
        switch (risultato) {
            case 1 -> partita.setEsitoPartita("vittoria");
            case -1 -> partita.setEsitoPartita("sconfitta");
            case 0 -> partita.setEsitoPartita("pareggio");
            default -> throw new IllegalStateException("Unexpected value: " + risultato);
        }
        partitaRepository.save(partita);
        return risultato;
    }

    public List<PartitaDTO> getPartiteByName(String namePlayer) {
        Player player = playerRepository.findByName(namePlayer)
                .orElseThrow(() -> new IllegalArgumentException("Player not found with id: " + namePlayer));
            List<Partita> partite = player.getPartiteGiocate();
            return partite.stream()
                    .map(PartitaDTO::partitaDTO)
                    .toList();
    }
}
