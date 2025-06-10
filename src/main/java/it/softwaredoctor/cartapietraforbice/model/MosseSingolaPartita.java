package it.softwaredoctor.cartapietraforbice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mossaSingolaPartita")
public class MosseSingolaPartita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoMossaPlayer;
    private String tipoMossaComputer;

    @ManyToOne
    @JoinColumn(name = "partita_id")
    private Partita partita;

    public MosseSingolaPartita(Mossa mossaComputer, Mossa mossaPlayer) {
        this.tipoMossaComputer = mossaComputer.getClass().getSimpleName().toLowerCase();
        this.tipoMossaPlayer = mossaPlayer.getClass().getSimpleName().toLowerCase();
    }
}
