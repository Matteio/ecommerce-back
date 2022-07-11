package it.esame.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "prodottocarrello")
public class ProdottoInAcquisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_prodcarrello", nullable = false)
    private Integer idprodcarrello;

    @Basic
    @Column(name="quantita")
    private int quantita;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne()
    @JoinColumn(name="prodotto")
    private Prodotto prodotto;

    @ManyToOne()
    @JoinColumn(name="compratore")
    private Utente compratore;

    /*@Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;*/

    /*@Override
    public int compareTo(ProdottoInAcquisto o) {
        return  prodotto.getNome().compareTo(o.getProdotto().getNome());
    }*/


}//ProdottoInAcquisto
