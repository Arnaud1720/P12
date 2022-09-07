package com.arnaud.p12.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "association")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Association implements Serializable {

    @Column(name = "asso_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NotNull
    private Integer id;
    @Column(name = "asso_rna",unique = true)
        private String numRNA;
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
    @ManyToOne
    @JsonIgnore
    private User user;
    @Column(name = "nbr_adherent")
    private int nbrAdherent = 0;
    @OneToMany(mappedBy = "associationAct")
    @JsonIgnore
    private Collection<Activites> activites;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
