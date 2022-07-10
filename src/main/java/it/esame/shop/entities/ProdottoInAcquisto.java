package it.esame.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name= "prodottoCarrello")
public class ProdottoInAcquisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_prodcarrello", nullable = false)
    private int idprodcarrello;

    @Basic
    @Column(name="quantita",nullable = true)
    private int quantita;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="prodotto")
    private Prodotto prodotto;

    @ManyToOne
    @JoinColumn(name="ordine")
    //@JsonIgnore
    @ToString.Exclude
    private Carrello ordine;

    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

}//ProdottoInAcquisto
