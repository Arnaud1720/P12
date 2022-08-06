package com.arnaud.p12.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity(name = "adherents")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Adherents {
    @Id
    private long id;
    @Column(name = "adherent_nom")
    private String nom;
    @Column(name = "adherent_license_start")
    private LocalDate licenseStart;
    @Column(name = "adherent_license_stop")
    private LocalDate licenseStop;
    @Column(name = "not_valid")
    private boolean notValid;
    @OneToOne
    private User user;
    @OneToOne
    private Association association;
    @Column(name = "is_adherent")
    private boolean isAdherent;

    public boolean isAdherent() {
        return isAdherent;
    }

    public void setAdherent(boolean adherent) {
        isAdherent = adherent;
    }
}
