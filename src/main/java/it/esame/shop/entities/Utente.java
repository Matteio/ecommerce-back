package it.esame.shop.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="utente")
public class Utente {

    @Id
    @Column(name= "cf", nullable = false, length=16)
    private String cf;

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
    @Basic
    @Column(name="email",nullable = true, length = 45)
    private String email;

    @Basic
    @Column(name="indirizzo",nullable = true, length = 45)
    private String indirizzo;

    /*@OneToMany(mappedBy = "utente", cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Ordine> ordini;
    */

}//Utente
