package com.arnaud.p12.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "activites")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activites {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "act_description")
    private String description;
    @Column(name = "act_date_debut")
    private LocalDate dateDebut;
    @Column(name = "act_date_fin")
    private LocalDate dateFin;
    @ManyToOne
    @JsonIgnore
    private User userAtc;
    @ManyToOne
    @JsonIgnore
    private Association associationAct;

}
//@JoinTable(name= "user_role",joinColumns = @JoinColumn(name= "user_id", referencedColumnName = "id") ,
//        inverseJoinColumns = @JoinColumn(name= "role_id", referencedColumnName = "role_id"))
