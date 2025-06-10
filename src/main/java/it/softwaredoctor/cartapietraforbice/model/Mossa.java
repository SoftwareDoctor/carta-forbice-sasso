package it.softwaredoctor.cartapietraforbice.model;

public interface Mossa {

    int confrontaMossa(Mossa mossaAvversaria);
}

/*
Java 21 -->
Il metodo confrontaMossa(Mossa mossaAvversaria) utilizza il pattern matching con il costrutto switch
per determinare a runtime di che tipo concreto è l'oggetto mossaAvversaria (Carta, Sasso o Forbice).
In base al tipo, esegue l'azione appropriata grazie al pattern matching introdotto in Java 21.
*/
