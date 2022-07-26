package com.arnaud.p12.service.impl;

import com.arnaud.p12.exception.EntityNotFoundException;
import com.arnaud.p12.exception.ErrorCode;
import com.arnaud.p12.model.Association;
import com.arnaud.p12.model.Permission;
import com.arnaud.p12.model.User;
import com.arnaud.p12.repository.AssociationRepository;
import com.arnaud.p12.repository.PermissionRepository;
import com.arnaud.p12.repository.UserRepository;
import com.arnaud.p12.service.AssociationService;
import com.arnaud.p12.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AssociationServiceImpl implements AssociationService {
    final AssociationRepository associationRepository;
    final UserRepository userRepository;
    final PermissionRepository permissionRepository;
    final UsersService usersService;

    public AssociationServiceImpl(AssociationRepository associationRepository, UserRepository userRepository, PermissionRepository permissionRepository, UsersService usersService) {
        this.associationRepository = associationRepository;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
        this.usersService = usersService;
    }



    @Override
    public List<Association> findAll() {
        return associationRepository.findAll();
    }


    @Override
    public void deleteById(long id) {
        associationRepository.deleteById(id);
    }



    @Override
    public User addPermissionToUser(String username, String name) {
        User usr = userRepository.findByUsername(username);
        Permission perm=permissionRepository.findByName(name);
        usr.getPermissions().add(perm);
        return usr;
    }

    @Override
    public Association save(Association association,long id) {
        User user = userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("", ErrorCode.USER_NOT_FOUND));
        usersService.addRoleToUser(user.getUsername(),"GESTIONAIRE");
        addPermissionToUser(user.getUsername(),"CONSULTER");
        addPermissionToUser(user.getUsername(),"EDITER");
        addPermissionToUser(user.getUsername(),"SUPPRIMER");

        return associationRepository.save(association);
    }

}
