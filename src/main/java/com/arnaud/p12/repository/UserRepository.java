package com.arnaud.p12.repository;

import com.arnaud.p12.model.Role;
import com.arnaud.p12.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin("*")
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);

    @Query("select u from users u where u.username like :kc")
    List<User> searchUser(@Param(value = "kc") String keyword);

    User findUserById(Integer id);


}
