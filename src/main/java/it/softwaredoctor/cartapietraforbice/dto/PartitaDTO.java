package it.softwaredoctor.cartapietraforbice.dto;

import it.softwaredoctor.cartapietraforbice.model.Partita;

import lombok.Builder;

@Builder
public record PartitaDTO(
        String esitoPartita,
        String codPartita
) {

    public static PartitaDTO partitaDTO(Partita partita) {
        return PartitaDTO.builder()
                .esitoPartita(partita.getEsitoPartita())
                .codPartita(partita.getCodPartita())
                .build();
    }
}
