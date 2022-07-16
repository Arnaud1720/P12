package com.arnaud.p12.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "association")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Association {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @Column(name = "nomAssociation")
    private String nom;
    @Column(name = "descriptionAssociation",length = 3500)
    private String description;
    @Column(name = "dateCreation")
    private LocalDateTime dateCreation;
    @OneToOne
    private User user;
}
