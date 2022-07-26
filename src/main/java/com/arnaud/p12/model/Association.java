package com.arnaud.p12.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDate dateCreation;
    @OneToOne
    @Nullable
    private User user;
    @Column(name = "nbr_adherent")
    private int nbrAdherent = 0;

}
