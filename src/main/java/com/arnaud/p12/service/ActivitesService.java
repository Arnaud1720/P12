package com.arnaud.p12.service;

import com.arnaud.p12.model.Activites;

import java.util.List;

public interface ActivitesService {
    Activites save(Activites activites, String username,int idAsso);
    Activites findById(int id);

    List<Activites> findall();

    List<Activites> findAllByAssoId(Activites activites,int id);
}
