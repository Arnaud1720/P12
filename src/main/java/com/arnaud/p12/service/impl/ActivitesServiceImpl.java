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

@Service
@Slf4j
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
    public Activites save(Activites activites, int idUser) {
        User currentUser = userRepository.findById(idUser).orElseThrow(()-> new EntityNotFoundException("Aucun utilisateur trouvé", ErrorCode.USER_NOT_FOUND));
        Association currentAsso= associationRepository.findByUsersId(idUser);
        activites.setUserAtc(currentUser);
        activites.setAssociationAct(currentAsso);
        return activitesRepository.save(activites);
    }

    @Override
    public Activites findById(int id) {
        return activitesRepository.findById(id).orElse(null);
    }
}
