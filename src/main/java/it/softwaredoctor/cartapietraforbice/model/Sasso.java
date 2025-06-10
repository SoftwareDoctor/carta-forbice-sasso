package it.softwaredoctor.cartapietraforbice.model;

public class Sasso implements Mossa {

    @Override
    public int confrontaMossa(Mossa mossaAvversaria) {
        return switch (mossaAvversaria) {
            case Carta c -> -1; // Sasso perde contro Carta
            case Sasso s -> 0; // Pareggio
            case Forbice f -> 1; // Sasso vince contro Forbice
            default -> 0; // Caso di default, non dovrebbe mai accadere
        };
    }
}
