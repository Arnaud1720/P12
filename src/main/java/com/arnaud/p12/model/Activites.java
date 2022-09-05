package com.arnaud.p12.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "activites")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activites implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "act_id")
    private Integer id;
    @Column(name = "act_description")
    private String description;
    @Column(name = "act_date_debut")
    private LocalDate dateDebut;
    @Column(name = "act_date_fin")
    private LocalDate dateFin;


    @ManyToOne
    @JsonIgnore
    private Association associationAct;

    @Column(name = "act_nbrs")
    private int participant = 0;

    @ManyToOne
    @JsonIgnore
    private User users;

    @ManyToMany
    @JoinTable(name= "adh_act",joinColumns = @JoinColumn(name= "act_id", referencedColumnName = "act_id") ,
            inverseJoinColumns = @JoinColumn(name= "adh_id", referencedColumnName = "adh_id"))

    private List<Adherents> adherents = new java.util.ArrayList<>();

}
