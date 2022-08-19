package com.arnaud.p12.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer role_id;
    private String role;
}
