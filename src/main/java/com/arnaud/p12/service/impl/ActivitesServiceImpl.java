package com.arnaud.p12.service.impl;

import com.arnaud.p12.exception.EntityNotFoundException;
import com.arnaud.p12.exception.ErrorCode;
import com.arnaud.p12.model.Activites;
import com.arnaud.p12.model.Adherents;
import com.arnaud.p12.model.Association;
import com.arnaud.p12.model.User;
import com.arnaud.p12.repository.ActivitesRepository;
import com.arnaud.p12.repository.AdherentRepository;
import com.arnaud.p12.repository.AssociationRepository;
import com.arnaud.p12.repository.UserRepository;
import com.arnaud.p12.service.ActivitesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ActivitesServiceImpl implements ActivitesService {
    final ActivitesRepository activitesRepository;
    final AssociationRepository associationRepository;
    final UserRepository userRepository;

    final AdherentRepository adherentRepository;
    public ActivitesServiceImpl(ActivitesRepository activitesRepository, AssociationRepository associationRepository, UserRepository userRepository, AdherentRepository adherentRepository) {
        this.activitesRepository = activitesRepository;
        this.associationRepository = associationRepository;
        this.userRepository = userRepository;
        this.adherentRepository = adherentRepository;
    }

    @Override
    public Activites save(Activites activites, String username,int idAsso) {
        User currentUser = userRepository.findByUsername(username).orElseThrow(()-> new EntityNotFoundException("Aucun utilisateur trouvé", ErrorCode.USER_NOT_FOUND));
        Association currentAsso= associationRepository.findById(idAsso).orElse(null);
        activites.setUsers( currentUser);
        activites.setAssociationAct(currentAsso);
        return activitesRepository.save(activites);
    }

    @Override
    public Activites findById(int id) {
        return activitesRepository.findById(id).orElse(null);
    }

    @Override
    public Activites addActToAdh(int adhId, int actId) {
        Adherents adh = adherentRepository.findById(adhId).orElse(null);
        Activites act = activitesRepository.findById(actId).orElse(null);
        assert act != null;
        act.getAdherents().add(adh);
        return act;
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



    @Override
    public Activites saveToAdh(int idAdh,int idAct) {
        Activites act = activitesRepository.findById(idAct).orElse(null);
        assert act != null;
        act.setParticipant(act.getParticipant()+1);
        return addActToAdh(idAdh,idAct);

    }

    @Override
    public void actDeleteById(int id) throws EntityNotFoundException {
        Activites activites = activitesRepository.findById(id).orElse(null);
        if(activites!=null && activites.getParticipant()>0){
            throw new EntityNotFoundException("Impossible de supprimer cette activité! cause: participant inscrit ",ErrorCode.STILL_RSGISTERED);
        }
        activitesRepository.deleteById(id);
    }
}
