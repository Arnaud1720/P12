package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Association implements Serializable {


    private Integer id;
    private String nom;
    private String description;
    @DateTimeFormat( pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateCreation;
    private String nomPresidentAsso;
    private String numTelAsso;
    private String email;
    private User user;
    private int nbrAdherent = 0;
    private Collection<Activites> activites;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
