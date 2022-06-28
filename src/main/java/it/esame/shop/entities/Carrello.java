package it.esame.shop.entities;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
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
    private int idCarrello;

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
