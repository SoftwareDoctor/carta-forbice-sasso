package it.softwaredoctor.cartapietraforbice.controller;

import it.softwaredoctor.cartapietraforbice.dto.PartitaDTO;
import it.softwaredoctor.cartapietraforbice.model.Partita;
import it.softwaredoctor.cartapietraforbice.service.GameService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/game")
@RestController
public class GameController {

    private final GameService gameService;

    @PostMapping("/player")
    public void createNewPlayer(@RequestParam String namePlayer) {
        gameService.createNewPlayer(namePlayer);
    }

    @PostMapping("/partita/{idPlayer}")
    public void createNewPartita(@PathVariable Long idPlayer) {
        gameService.createNewPartita(idPlayer);
    }

    @PostMapping("/start")
    public ResponseEntity<Integer> startGame(@RequestParam String codPartita, @RequestParam String playerMossa) {
        int risultato = gameService.startGame(codPartita, playerMossa);
        return ResponseEntity.ok(risultato);
    }

    @GetMapping("/partite")
    public ResponseEntity <List<PartitaDTO>> getPartiteGiocate(@RequestParam String namePlayer) {
        List<PartitaDTO> partiteGiocate = gameService.getPartiteByName(namePlayer);
        return ResponseEntity.ok(partiteGiocate);
    }
}
