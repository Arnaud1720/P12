package com.arnaud.p12.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "association")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Association implements Serializable {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NotNull
    private long id;
    @Column(name = "nom_Association")
    private String nom;
    @Column(name = "description_Association",length = 3500)
    private String description;
    @Column(name = "date_Creation")
    @DateTimeFormat( pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateCreation;
    @Column(name = "nom_president_association")
    private String nomPresidentAsso;
    @Column(name = "numero_tel_asso")
    private String numTelAsso;
    @Column(name = "email_asso")
    private String email;
    @OneToOne
    @Nullable
    private User user;
    @Column(name = "nbr_adherent")
    private int nbrAdherent = 0;

}
