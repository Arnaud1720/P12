package com.arnaud.p12.service;

import com.arnaud.p12.model.Adherents;

public interface AdherentServices {
     Adherents saveNewAdherent(long idUser, long idAsso,Adherents adherents);
     Adherents findById(long id);
     void deleteAdherent(long idUser,long idAsso,Adherents adherents);
     Adherents updateLicenseValidity(long idUser,long idAsso,Adherents adherents);
     boolean isValidLicense();
}
