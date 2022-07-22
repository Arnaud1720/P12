package com.arnaud.p12.service;


import com.arnaud.p12.model.Role;
import com.arnaud.p12.model.User;

import java.util.List;

public interface UsersService {
    void deleteByUserId(long id);
    User findByUserId(long id);
    User saveUser(User user);
    User findUserByUsername (String username);
    Role addRole(Role role);
    User addRoleToUser(String username, String rolename);
    List<User> findall();


}
