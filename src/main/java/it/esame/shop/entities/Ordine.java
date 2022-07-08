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
@Table(name="ordine")
public class Ordine  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_ordine", nullable = false)
    private int idordine;

    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAcquisto;

    @ManyToOne
    @JoinColumn(name = "utente")
    private Utente utente;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "ordine")
    private List<ProdottoInAcquisto> prodottoInAcquisto;

}//Ordine
