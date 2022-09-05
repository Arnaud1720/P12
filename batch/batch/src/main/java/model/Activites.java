package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activites implements Serializable {
    private Integer id;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Association associationAct;
    private int participant = 0;
    private User users;
    private List<Adherents> adherents = new java.util.ArrayList<>();

}
