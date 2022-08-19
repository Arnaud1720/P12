package com.arnaud.p12.service;

import com.arnaud.p12.model.Adherents;

public interface AdherentServices {


     Adherents saveNewAdherent(String username,int idAsso, Adherents adherents);

     Adherents findById(Integer id);
     void deleteAdherent(Integer idUser,Integer idAsso,Adherents adherents);
     Adherents updateLicenseValidity(Integer idUser,Integer idAsso,Adherents adherents);


     boolean isValidLicense();
}
