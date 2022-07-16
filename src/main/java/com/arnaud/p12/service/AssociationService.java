package com.arnaud.p12.service;


import com.arnaud.p12.model.Association;

import java.util.List;

public interface AssociationService {

    Association findByUsersId(long id);
    List<Association> findAll();
    Association save(Association association, long id);
    void deleteById(long id);

}
