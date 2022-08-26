package com.arnaud.p12.service.impl;

import com.arnaud.p12.exception.EntityNotFoundException;
import com.arnaud.p12.exception.ErrorCode;
import com.arnaud.p12.model.Activites;
import com.arnaud.p12.model.Association;
import com.arnaud.p12.model.User;
import com.arnaud.p12.repository.ActivitesRepository;
import com.arnaud.p12.repository.AssociationRepository;
import com.arnaud.p12.repository.UserRepository;
import com.arnaud.p12.service.ActivitesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ActivitesServiceImpl implements ActivitesService {
    final ActivitesRepository activitesRepository;
    final AssociationRepository associationRepository;
    final UserRepository userRepository;
    public ActivitesServiceImpl(ActivitesRepository activitesRepository, AssociationRepository associationRepository, UserRepository userRepository) {
        this.activitesRepository = activitesRepository;
        this.associationRepository = associationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Activites save(Activites activites, String username,int idAsso) {
        User currentUser = userRepository.findByUsername(username).orElseThrow(()-> new EntityNotFoundException("Aucun utilisateur trouvé", ErrorCode.USER_NOT_FOUND));
        Association currentAsso= associationRepository.findById(idAsso).orElse(null);
        activites.setUserAtc(currentUser);
        activites.setAssociationAct(currentAsso);
        if(currentUser.getRoles().contains("Adherent")){
            return null;
        }
        return activitesRepository.save(activites);
    }

    @Override
    public Activites findById(int id) {
        return activitesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Activites> findall() {
        return activitesRepository.findAll();
    }

    @Override
    public List<Activites> findAllByAssoId(Activites activites,int id) {
        Association association =associationRepository.findById(id).orElseThrow(()->new EntityNotFoundException("aucun association ne correspond a ce numéro",ErrorCode.ASSOCIATION_NOT_FOUND));
        activites.setAssociationAct(association);
        return activitesRepository.findAllByAssociationActId(id);
    }
}
