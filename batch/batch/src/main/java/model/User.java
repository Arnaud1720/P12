package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    private Integer id;
    private String lastName;
    private String fristName;
    private String phoneNumber;
    private String adressPostal;
    private String password;
    private String email;
    private String username;
    private boolean enabled;

    private Collection<Adherents> adherents;

    private Collection<Association> association;

    private List<Role> roles = new ArrayList<>();

    private List<Permission> permissions;
    private Collection<Activites> activites;

    public Integer setId(Integer id) {
      return   this.id = id;
    }

}

