package com.arnaud.p12.service.impl;

import com.arnaud.p12.exception.EntityNotFoundException;
import com.arnaud.p12.exception.ErrorCode;
import com.arnaud.p12.model.Adherents;
import com.arnaud.p12.model.Association;
import com.arnaud.p12.model.User;
import com.arnaud.p12.repository.AdherentRepository;
import com.arnaud.p12.repository.AssociationRepository;
import com.arnaud.p12.repository.RoleRepository;
import com.arnaud.p12.repository.UserRepository;
import com.arnaud.p12.service.AdherentServices;
import com.arnaud.p12.service.UsersService;
import com.arnaud.p12.service.impl.javaMailSender.JavaMailSenderImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class AdherentServiceImpl implements AdherentServices {
    private final UserRepository userRepository;
    private final AssociationRepository associationRepository;
    private final AdherentRepository adherentRepository;
    private final UsersService usersService;
    private final RoleRepository roleRepository;
    private final JavaMailSenderImpl javaMailSender;

    public AdherentServiceImpl(UserRepository userRepository, AssociationRepository associationRepository, AdherentRepository adherentRepository, UsersService usersService, RoleRepository roleRepository, JavaMailSenderImpl javaMailSender) {
        this.userRepository = userRepository;
        this.associationRepository = associationRepository;
        this.adherentRepository = adherentRepository;
        this.usersService = usersService;
        this.roleRepository = roleRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public Adherents saveNewAdherent(String username,int idAsso, Adherents adherents) {
         String url = "http://localhost:4200/login";
         String urlInscription = "http://localhost:4200/creation/utilisateur";
         User user = userRepository.findByUsername(username).orElseThrow(()->new EntityNotFoundException("", ErrorCode.USER_NOT_FOUND));
//         boolean isAdh = roleRepository.findIfArdlyAdh(username);
        Association association = associationRepository.findById(idAsso).orElseThrow(()->new EntityNotFoundException("",ErrorCode.ASSOCIATION_NOT_FOUND));
        association.setNbrAdherent(association.getNbrAdherent()+1);
        adherents.setUser(user);
        adherents.setAssociation(association);
        adherents.setLicenseStart(LocalDate.now());
        adherents.setLicenseStop(LocalDate.now().plusMonths(6));
        adherents.setNotValid(false);


        javaMailSender.sendEmail(user.getEmail(),"nouvelle adherent","nom:"+user.getFristName()+
                "\n prénom: "+user.getLastName()+"\n date début : "+adherents.getLicenseStart()+"\n date fin "+adherents.getLicenseStop()+"" +
                "\n pour acceder a votre compte rendez vous sur "+" "+url+"si vous n'etes pas encore inscrit rendez vous sur"+" "+urlInscription);
        if(adherents.isAdherent()) {
            log.warn("dèja adherent");
            return adherentRepository.save(adherents);
        }else
        {
            adherents.setAdherent(true);
            usersService.addRoleToUser(user.getUsername(),"ADHERENT");
            return adherentRepository.save(adherents);

        }
    }

    @Override
    public Adherents findById(Integer id) {
        return adherentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAdherent(Integer idUser, Integer idAsso, Adherents adherents) {
        User user = userRepository.findById(idUser).orElseThrow(()->new EntityNotFoundException("", ErrorCode.USER_NOT_FOUND));
        Association association = associationRepository.findById(idAsso).orElseThrow(()->new EntityNotFoundException("",ErrorCode.ASSOCIATION_NOT_FOUND));
        adherents.setUser(user);
        adherents.setAssociation(association);
        usersService.deleteRoleToUser(user.getUsername(),"ADHERENT");
        association.setNbrAdherent(-1);
        if(association.getNbrAdherent()<0){
            association.setNbrAdherent(0);
        }
        javaMailSender.sendEmail(user.getEmail()," adherent supprimer","nom:"+user.getFristName()+
                "\n prénom: "+user.getLastName()+"\n date début : "+adherents.getLicenseStart()+"\n date fin "+adherents.getLicenseStop()+"" +
                "\n vôtre license a été supprimer" );
        adherents.setAdherent(false);
        adherentRepository.delete(adherents);
    }

    @Override
    public Adherents updateLicenseValidity(Integer idUser, Integer idAsso, Adherents adherents) {
        LocalDate dateFin = adherents.getLicenseStop();

        User user = userRepository.findById(idUser).orElseThrow(()->new EntityNotFoundException("", ErrorCode.USER_NOT_FOUND));
        Association association = associationRepository.findById(idAsso).orElseThrow(()->new EntityNotFoundException("",ErrorCode.ASSOCIATION_NOT_FOUND));
        adherents.setUser(user);
        adherents.setAssociation(association);
        if(isValidLicense()){
                adherents.setLicenseStop(dateFin.plusMonths(3));
        }else {
            log.info("pas hors delai");
        }
        return adherentRepository.save(adherents);
    }


    @Override
    public boolean isValidLicense() {
        return adherentRepository.isValidLicense();
    }
}
