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
@Table(name="prodotto")
public class Prodotto  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_prodotto", nullable = false)
    private int idprodotto;

    @Basic
    @Column(name="nome",nullable = true, length = 45)
    private String nome;

    /*@Basic
    @Column(name="barCode",nullable = true, length = 45)
    private String barCode;
     */

    @Basic
    @Column(name="descrizione",nullable = true, length = 45)
    private String descrizione;
    @Basic
    @Column(name="prezzo",nullable = true)
    private Double prezzo;
    @Basic
    @Column(name="disponibilita",nullable = true)
    private int disponibilita;
    @Basic
    @Column(name="image",nullable = true, length = 70)
    private String image;

   /* @Version
    @JsonIgnore
    private long version;
    */

    @OneToMany(targetEntity = Carrello.class, mappedBy="prodotto", cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    private List<Carrello> carrello;


}//Prodotto
