package it.esame.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_utente", nullable = false)
    private int idutente;

    /*@Basic
    @Column(name="codice",nullable = true, length = 45)
    private String codice;*/
    @Basic
    @Column(name="nome", length = 45)
    private String nome;
    @Basic
    @Column(name="cognome",nullable = true, length = 45)
    private String cognome;
    @Basic
    @Column(name="telefono",nullable = true, length = 45)
    private String telefono;
    /*@Basic
    @Column(name="email",nullable = true, length = 45)
    private String email;
     */
    @Basic
    @Column(name="indirizzo",nullable = true, length = 45)
    private String indirizzo;


    @OneToMany(mappedBy = "utente", cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Ordine> ordini;


}//Utente
