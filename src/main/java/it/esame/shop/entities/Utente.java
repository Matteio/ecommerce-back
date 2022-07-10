package it.esame.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="utente")
public class Utente {

    @Id
    @Column(name= "cf", nullable = false, length=16)
    private String cf;
    @Basic
    @Column(name="nome", length = 45)
    private String nome;
    @Basic
    @Column(name="cognome",nullable = true, length = 45)
    private String cognome;
    @Basic
    @Column(name="telefono",nullable = true, length = 45)
    private String telefono;
    @Basic
    @Column(name="email",nullable = true, length = 45)
    private String email;
    @Basic
    @Column(name="indirizzo",nullable = true, length = 45)
    private String indirizzo;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "compratore")
    private List<ProdottoInAcquisto> carrello=new ArrayList<>();

}//Utente
