package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Adherents {

    private Integer id;
    private String nom;
    private LocalDate licenseStart;
    private LocalDate licenseStop;
    private boolean notValid;

    private User userAdherent;
    private Association association;
    private boolean isAdherent;


    public boolean isAdherent() {
        return isAdherent;
    }

    public void setAdherent(boolean adherent) {
        isAdherent = adherent;
    }
}
