/*package it.esame.shop.entities;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name= "acquisto")
public class Acquisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_acquisto", nullable = false)
    private int idacquisto;

    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAcquisto;

    @ManyToOne
    @JoinColumn(name = "utente")
    private Utente utente;

}//Carrello*/