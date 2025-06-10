package it.softwaredoctor.cartapietraforbice.model;

public class Forbice implements Mossa {

    @Override
    public int confrontaMossa(Mossa mossaAvversaria) {
       return switch(mossaAvversaria) {
             case Carta c -> 1; // Forbice vince contro Carta
             case Sasso s -> -1; // Forbice perde contro Sasso
             case Forbice f -> 0; // Pareggio
             default -> 0; // Caso di default, non dovrebbe mai accadere
       };
    }
}
