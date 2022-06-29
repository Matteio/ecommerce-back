package it.esame.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="carrello")
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCarrello", nullable = false)
    private int idCarrello;

    @Basic
    @Column(name="quantita",nullable = true)
    private int quantita;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="prodotto")
    private Prodotto prodotto;

    @ManyToOne
    @JoinColumn(name="ordine")
    @JsonIgnore
    @ToString.Exclude
    private Ordine ordine;

}//Carrello
