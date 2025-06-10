package it.softwaredoctor.cartapietraforbice.model;

public class Carta implements Mossa {

//    @Override
//    public int confrontaMossa(Mossa mossaAvversaria) {
//        if (mossaAvversaria instanceof Carta) {
//            return 0; // Pareggio
//        } else if (mossaAvversaria instanceof Sasso) {
//            return 1; // Carta vince contro Sasso
//        } else if (mossaAvversaria instanceof Forbice) {
//            return -1; // Carta perde contro Forbice
//        }
//        return 0; // Caso di default, non dovrebbe mai accadere
//    }

    // forma alternativa di implementazione usando switch a pattern matching
    @Override
    public int confrontaMossa(Mossa mossaAvversaria) {
      return switch(mossaAvversaria) {
            case Carta c -> 0; // Pareggio
            case Sasso s -> 1; // Carta vince contro Sasso
            case Forbice f -> -1; // Carta perde contro Forbice
            default -> 0; // Caso di default, non dovrebbe mai accadere
      };
    }
}
