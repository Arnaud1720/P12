package com.arnaud.p12.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class User implements Serializable {


    @Id
    @Column(name = "user_id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "frist_name")
    private String fristName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "adress_postal")
    private String adressPostal;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "username",unique = true)
    private String username;
    private boolean enabled;

    @JsonIgnore
    @OneToMany(mappedBy = "userAdherent")
    private Collection<Adherents> adherents;

    @OneToMany(mappedBy = "user")
    private Collection<Association> association;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name= "user_role",joinColumns = @JoinColumn(name= "user_id", referencedColumnName = "user_id") ,
            inverseJoinColumns = @JoinColumn(name= "role_id", referencedColumnName = "role_id"))
    private List<Role> roles = new java.util.ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "permission_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "p_permission"))
    private List<Permission> permissions;
    @OneToMany(mappedBy = "users")
    private Collection<Activites> activites;


    public Integer setId(Integer id) {
      return   this.id = id;
    }

}

