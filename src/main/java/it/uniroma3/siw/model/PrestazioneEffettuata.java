package it.uniroma3.siw.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Entity
public class PrestazioneEffettuata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "barbiere")
    @NotNull
    private Utente barbiere;

    @ManyToOne
    @JoinColumn(nullable = false, name = "utente")
    @NotNull
    private Utente utente;
    
    @Column(nullable = false,name = "data_prestazione")
    @NotNull
    @FutureOrPresent
    private Date dataPrestazione;
    
    @ManyToOne
    @Column(nullable = false)
    private TipoPrestazione prestazione;
}
