package com.arnaud.p12.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "association")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Association implements Serializable {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @NotNull
    private long id;
    @Column(name = "nom_Association")
    private String nom;
    @Column(name = "description_Association",length = 3500)
    private String description;
    @Column(name = "date_Creation")
    private LocalDateTime dateCreation;
    @OneToOne
    @Nullable
    private User user;
}
