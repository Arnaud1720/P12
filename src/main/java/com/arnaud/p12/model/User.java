package com.arnaud.p12.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User implements Serializable {


    @Id
    @Column(name = "id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name= "user_role",joinColumns = @JoinColumn(name= "user_id", referencedColumnName = "id") ,
            inverseJoinColumns = @JoinColumn(name= "role_id", referencedColumnName = "role_id"))
    @Nullable
    private List<Role> roles = new java.util.ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "permission_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "p_permission"))
    private List<Permission> permissions;
}
