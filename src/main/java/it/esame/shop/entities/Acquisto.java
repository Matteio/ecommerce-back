package it.esame.shop.entities;

import lombok.*;
import org.apache.james.mime4j.field.datetime.DateTime;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
//import java.util.List;

@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "acquisto")
public class Acquisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acquisto", nullable = false)
    private Integer idacquisto;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    //@Basic
    @Column(name = "data_acquisto")
    private Date dataAcquisto;

    @ManyToOne()
    @JoinColumn(name="compratore_a")
    private Utente compratore;

    @ManyToOne
    @JoinColumn(name="prodotto_a")
    private Prodotto prodotto;

    @Column(name="quantita")
    private int quantity;

    /*@Column(name="prezzo_fissato")
    private double prezzo_fissato;*/

    @Column(name="done")
    private boolean done;

}
