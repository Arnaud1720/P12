package com.arnaud.p12.repository;

import com.arnaud.p12.model.Adherents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherentRepository extends JpaRepository<Adherents,Long> {
    @Query(nativeQuery = true,value = "SELECT * from adherents where adherent_license_stop < now()")
    boolean isValidLicense();
}
