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
import com.arnaud.p12.service.impl.javaMailSender.JavaMailSenderImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class AssociationServiceImpl implements AssociationService {
    final AssociationRepository associationRepository;
    final UserRepository userRepository;
    final PermissionRepository permissionRepository;
    final UsersService usersService;
    @Autowired
    private JavaMailSenderImpl javaMailSender;
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


    /**
     *
     * @param id
     */
    //TODO trouver un moyen de remove les perms dans la méthode deleteById
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
        String url = "http://localhost:4200/login";
        User user = userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("", ErrorCode.USER_NOT_FOUND));
        association.setDateCreation(LocalDateTime.now());
        usersService.addRoleToUser(user.getUsername(),"GESTIONAIRE");
        addPermissionToUser(user.getUsername(),"CONSULTER");
        addPermissionToUser(user.getUsername(),"EDITER");
        addPermissionToUser(user.getUsername(),"SUPPRIMER");
        javaMailSender.sendEmail(user.getEmail(),"création association ","nom:"+user.getFristName()+
                "\n prénom: "+user.getLastName()+"\n date début : "+association.getDateCreation()+"\n "+
                "\n votre association a été référencer avec sucès, pour consulter vôtre association dans nôtre liste redez-vous sur "+url);
        return associationRepository.save(association);
    }

    @Override
    public void removePermissionToUser(String username, String name) {
        User usr = userRepository.findByUsername(username);
        Permission perm=permissionRepository.findByName(name);
        usr.getPermissions().remove(perm);
    }

}
