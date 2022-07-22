package com.arnaud.p12.service.impl;


import com.arnaud.p12.exception.EntityNotFoundException;
import com.arnaud.p12.exception.ErrorCode;
import com.arnaud.p12.model.Role;
import com.arnaud.p12.model.User;
import com.arnaud.p12.repository.RoleRepository;
import com.arnaud.p12.repository.UserRepository;
import com.arnaud.p12.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


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
        userRepository.save(user);
                    return addRoleToUser(user.getUsername(),"USER");

    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User addRoleToUser(String username, String rolename) {
        User usr = userRepository.findByUsername(username);
        Role role = roleRepository.findByRole(rolename);
        usr.getRoles().add(role);
        return usr;
    }

    @Override
    public List<User> findall() {
        return userRepository.findAll();
    }

    @Override
    public void deleteByUserId(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUserId(long id) {

        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("l'utilisateur n'existe pas", ErrorCode.USER_NOT_FOUND));
    }


}
