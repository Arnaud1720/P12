package com.arnaud.p12.service;

import com.arnaud.p12.model.Activites;

import java.util.List;

public interface ActivitesService {
    Activites save(Activites activites, int idUser);
    Activites findById(int id);
}
