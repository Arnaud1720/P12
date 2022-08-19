package com.arnaud.p12.service.impl;


import com.arnaud.p12.exception.EntityNotFoundException;
import com.arnaud.p12.exception.ErrorCode;
import com.arnaud.p12.model.Role;
import com.arnaud.p12.model.User;
import com.arnaud.p12.repository.RoleRepository;
import com.arnaud.p12.repository.UserRepository;
import com.arnaud.p12.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@Transactional
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;


    public UsersServiceImpl(
            BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        // par défaut quand un utilisateur est enregister il dispose du rôle Utilisateur
        user.setEnabled(true);
        userRepository.save(user);
                    return addRoleToUser(user.getUsername(),"USER");

    }

//    @Override
//    public User updateUser(User userBody, String username) throws EntityNotFoundException {
//        // Objet null recupéré
//        userRepository.findUserByUsername(username);
//        userBody.setEmail(userBody.getEmail());
//        userBody.setPassword(bCryptPasswordEncoder.encode(userBody.getPassword()));
//        userBody.setPhoneNumber(userBody.getPhoneNumber());
//        userBody.setRoles(userBody.getRoles());
//        if(!userBody.getRoles().isEmpty()){
//            return userRepository.save(userBody);
//        }
//        addRoleToUser(userBody.getUsername(),"USER");
//        return userRepository.save(userBody);
//    }


    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User updateUserWithId(Integer id) throws EntityNotFoundException{
        User user = userRepository.findUserById(id);
        user.setEmail(user.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPhoneNumber(user.getPhoneNumber());
        user.setRoles(user.getRoles());
        if(!user.getRoles().isEmpty()){
            return userRepository.save(user);
        }
        addRoleToUser(user.getUsername(),"USER");
        return userRepository.save(user);
    }


    @Override
    public User addRoleToUser(String username, String rolename) {
        User usr = userRepository.findByUsername(username).orElseThrow(()->new EntityNotFoundException("aucun utilisateur ne correspond a ce pseudo",ErrorCode.USER_NOT_FOUND));
        Role role = roleRepository.findByRole(rolename);
        usr.getRoles().add(role);
        return usr;
    }

    @Override
    public List<User> findall() {
        return userRepository.findAll();
    }

    @Override
    public void deleteRoleToUser(String username, String rolename) {
        User usr = userRepository.findByUsername(username).orElseThrow(()->new EntityNotFoundException("aucun utilisateur ne correspond a ce pseudo",ErrorCode.USER_NOT_FOUND));
        Role role = roleRepository.findByRole(rolename);
        assert usr.getRoles() != null;
        usr.getRoles().remove(role);
    }

    @Override
    public List<User> searchUser(String keyword) {
        List<User> userSearch = userRepository.searchUser(keyword);
        if(userSearch.isEmpty()){
            throw new EntityNotFoundException("aucun utilisateur ne correspond a vôtre recherche",ErrorCode.USER_NOT_FOUND);
        }else
            return userSearch;
    }






    @Override
    public void deleteByUserId(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUserId(Integer id) {

        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("l'utilisateur n'existe pas", ErrorCode.USER_NOT_FOUND));
    }




}
