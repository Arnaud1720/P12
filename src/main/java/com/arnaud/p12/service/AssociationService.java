package com.arnaud.p12.service;


import com.arnaud.p12.model.Association;
import com.arnaud.p12.model.User;

import java.util.List;

public interface AssociationService {

    List<Association> findAll();
    void deleteById(long id);
    User addPermissionToUser(String username, String permission);
    Association save(Association association,long id);
    void  removePermissionToUser(String username, String name);
}
