package com.arnaud.p12.repository;

import com.arnaud.p12.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);


}
