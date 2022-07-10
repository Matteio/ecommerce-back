package it.esame.shop.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name= "carrello")
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_carrello", nullable = false)
    private int idcarrello;

    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAcquisto;

    @ManyToOne
    @JoinColumn(name = "utente")
    private Utente utente;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "ordine")
    private List<ProdottoInAcquisto> prodottoInAcquisto;

}//Carrello
