/*
package it.esame.shop.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="indirizzo")
public class Indirizzo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String indirizzo,
    citta,
    paese,
    stato;
    private int codice_postale;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Utente",nullable = false)
    private Utente utente;

}//Indirizzo

 */
