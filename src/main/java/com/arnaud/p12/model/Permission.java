package com.arnaud.p12.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable {
    @Id
    @Column(name = "id_permission")
    private long id;
    @Column(name = "permission_name")
    private String name;
}
