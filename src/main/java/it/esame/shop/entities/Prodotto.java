package it.esame.shop.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
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
    private int idProdotto;

    private String nome,
    barCode,
    descrizione;
    private Double prezzo;
    private int disponibilita;

    @Version
    @JsonIgnore
    private long version;

    @OneToMany(targetEntity = Carrello.class, mappedBy="prodotto", cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    private List<Carrello> carrello;


}//Prodotto
