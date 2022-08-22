package com.arnaud.p12.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity(name = "activites")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activites {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "act_description")
    private String description;
    @Column(name = "act_date_debut")
    private String dateDebut;
    @Column(name = "act_date_fin")
    private String dateFin;
    @ManyToOne
    @JsonIgnore
    private User userAtc;
    @ManyToOne
    @JsonIgnore
    private Association associationAct;

}
