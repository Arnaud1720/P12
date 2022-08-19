package com.arnaud.p12.repository;

import com.arnaud.p12.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

    @Query(nativeQuery = true ,value ="select *  from roles  r where r.role_id=5 ")
    boolean findIfArdlyAdh(String username);
}
