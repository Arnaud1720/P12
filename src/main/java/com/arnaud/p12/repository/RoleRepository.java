package com.arnaud.p12.repository;

import com.arnaud.p12.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
