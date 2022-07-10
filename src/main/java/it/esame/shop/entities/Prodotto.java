package it.esame.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name="prodotto")
@NoArgsConstructor
@AllArgsConstructor
public class Prodotto  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_prodotto", nullable = false)
    private int idprodotto;

    @Basic
    @Column(name="nome",nullable = false, length = 45, unique = true)
    private String nome;

    @Basic
    @Column(name="descrizione",nullable = true, length = 2000)
    private String descrizione;
    @Basic
    @Column(name="prezzo",nullable = false)
    private Double prezzo;
    @Basic
    @Column(name="disponibilita",nullable = false)
    private int disponibilita;
    @Basic
    @Column(name="image",nullable = true, length = 70)
    private String image;

    /*@Version
    @JsonIgnore
    @Column(name="version", nullable = false)
    private long version;*/

    /*@OneToMany(targetEntity = ProdottoInAcquisto.class, mappedBy="prodotto", cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    private List<ProdottoInAcquisto> prodottoInAcquisto;
    */

}//Prodotto
