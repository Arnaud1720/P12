package com.arnaud.p12.service;

import com.arnaud.p12.model.Adherents;
import com.arnaud.p12.model.User;

import java.util.List;

public interface AdherentServices {


     Adherents saveNewAdherent(String username,int idAsso, Adherents adherents);
     Adherents findById(Integer id);
     void deleteAdherent(Integer idUser,Integer idAsso,Adherents adherents);
     Adherents updateLicenseValidity(Integer idUser,Integer idAsso,Adherents adherents);

     List<Adherents> findAllByAssociationId(int idAsso);
     boolean isValidLicense();
}
