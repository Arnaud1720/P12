package com.arnaud.p12.repository;

import com.arnaud.p12.model.Activites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin("*")
@Repository
public interface ActivitesRepository extends JpaRepository<Activites,Integer> {
    
}
