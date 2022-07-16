package com.arnaud.p12.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {


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
    @Column(name = "username")
    private String username;
    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id") ,
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;
    private boolean enabled;
}
