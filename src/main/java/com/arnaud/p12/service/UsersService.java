package com.arnaud.p12.service;


import com.arnaud.p12.model.Role;
import com.arnaud.p12.model.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {


    User saveUser(User user);
//     User updateUser(User userBody,String username);
    Optional<User> findUserByUsername(String username);

    Role addRole(Role role);

    User updateUserWithId(Integer id);

    User addRoleToUser(String username, String rolename);

    List<User> findall();

    void deleteRoleToUser(String username, String rolename);

    List<User> searchUser(String keyword);

    void deleteByUserId(Integer id);

    User findByUserId(Integer id);
}
