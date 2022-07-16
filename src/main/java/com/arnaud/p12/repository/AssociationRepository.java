package com.arnaud.p12.repository;

import com.arnaud.p12.model.Association;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociationRepository extends JpaRepository<Association,Long> {
    @Query("select a from association a where a.user.id = :id")
    Association findByUsersId(@Param("id")long id);

    @Query("select a from association a")
    List<Association> findAll();


}
