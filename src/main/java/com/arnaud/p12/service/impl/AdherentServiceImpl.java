package com.arnaud.p12.service.impl;

import com.arnaud.p12.exception.EntityNotFoundException;
import com.arnaud.p12.exception.ErrorCode;
import com.arnaud.p12.model.Adherents;
import com.arnaud.p12.model.Association;
import com.arnaud.p12.model.User;
import com.arnaud.p12.repository.AdherentRepository;
import com.arnaud.p12.repository.AssociationRepository;
import com.arnaud.p12.repository.UserRepository;
import com.arnaud.p12.service.AdherentServices;
import com.arnaud.p12.service.UsersService;
import com.arnaud.p12.service.impl.javaMailSender.JavaMailSenderImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
public class AdherentServiceImpl implements AdherentServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssociationRepository associationRepository;
    @Autowired
    private AdherentRepository adherentRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Override
    public Adherents saveNewAdherent( long idUser, long idAsso,Adherents adherents) {
         String url = "http://localhost:4200/login";
        User user = userRepository.findById(idUser).orElseThrow(()->new EntityNotFoundException("", ErrorCode.USER_NOT_FOUND));
        Association association = associationRepository.findById(idAsso).orElseThrow(()->new EntityNotFoundException("",ErrorCode.ASSOCIATION_NOT_FOUND));
        adherents.setUser(user);
        adherents.setAssociation(association);
        adherents.setLicenseStart(LocalDate.now());
        adherents.setLicenseStop(LocalDate.now().plusMonths(6));
        association.setNbrAdherent(+1);
        usersService.addRoleToUser(user.getUsername(),"ADHERENT");
        javaMailSender.sendEmail(user.getEmail(),"nouvelle adherent","nom:"+user.getFristName()+
                "\n prénom: "+user.getLastName()+"\n date début : "+adherents.getLicenseStart()+"\n date fin "+adherents.getLicenseStop()+"" +
                "\n pour acceder a votre compte rendez vous sur "+" "+url );
         adherents.setAdherent(true);
        if(adherents.isAdherent()){
            log.warn("dèja adherent");

        }
        return adherentRepository.save(adherents);
    }

    @Override
    public Adherents findById(long id) {
        return adherentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAdherent(long idUser, long idAsso, Adherents adherents) {
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
    public Adherents updateLicenseValidity(long idUser, long idAsso, Adherents adherents) {
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
