package com.arnaud.p12.service;


import com.arnaud.p12.model.Association;
import com.arnaud.p12.model.User;

import java.util.List;

public interface AssociationService {
    List<Association> findAll();

    //TODO trouver un moyen de remove les perms dans la méthode deleteById
    void deleteById(Integer id);

    User addPermissionToUser(String username, String name);

    Association save(Association association, String username);

    void removePermissionToUser(String username, String name);
}
