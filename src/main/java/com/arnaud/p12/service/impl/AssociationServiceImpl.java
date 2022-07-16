package com.arnaud.p12.service.impl;

import com.arnaud.p12.exception.EntityNotFoundException;
import com.arnaud.p12.exception.ErrorCode;
import com.arnaud.p12.model.Association;
import com.arnaud.p12.model.User;
import com.arnaud.p12.repository.AssociationRepository;
import com.arnaud.p12.repository.UserRepository;
import com.arnaud.p12.service.AssociationService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssociationServiceImpl implements AssociationService {
        final AssociationRepository associationRepository;
        final UserRepository userRepository;

    public AssociationServiceImpl(AssociationRepository associationRepository, UserRepository userRepository) {
        this.associationRepository = associationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Association findByUsersId(long id)throws EntityNotFoundException {
        return associationRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("cette association n'existe pas", ErrorCode.ASSOCIATION_NOT_FOUND));
    }

    @Override
    public List<Association> findAll() {
        return associationRepository.findAll();
    }

    @Override
    public Association save(Association association,long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("l'utilisateur n'existe pas",ErrorCode.USER_NOT_FOUND));
        association.setUser(user);
        return associationRepository.save(association);
    }

    @Override
    public void deleteById(long id) {
        associationRepository.deleteById(id);
    }


}
