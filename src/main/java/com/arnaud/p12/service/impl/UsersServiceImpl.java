package com.arnaud.p12.service.impl;


import com.arnaud.p12.exception.EntityNotFoundException;
import com.arnaud.p12.exception.ErrorCode;
import com.arnaud.p12.model.Role;
import com.arnaud.p12.model.User;
import com.arnaud.p12.repository.RoleRepository;
import com.arnaud.p12.repository.UserRepository;
import com.arnaud.p12.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


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
    public void deleteRoleToUser(String username, String rolename) {
        User usr = userRepository.findByUsername(username);
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
    public User getAccountUser(long accountId, int page, int size) {
        Page<User> usersOperation = userRepository.findById(accountId, PageRequest.of(page,size));
        List<User> usersContents= usersOperation.getContent().stream().toList();
        return null;
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
