package it.softwaredoctor.cartapietraforbice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partita")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codPartita;
    private String stato;
    private String esitoPartita;

    @JsonIgnore

    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @OneToMany(mappedBy = "partita", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<MosseSingolaPartita> mosseSingolaPartita = new ArrayList<>();

    @PrePersist
    public void initializeDefaults() {
        if (stato == null) stato = "in attesa";
        esitoPartita = "da definire";
    }
}
