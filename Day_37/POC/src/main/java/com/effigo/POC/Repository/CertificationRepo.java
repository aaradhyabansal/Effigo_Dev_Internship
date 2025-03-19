package com.effigo.POC.Repository;

import com.effigo.POC.Model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepo extends JpaRepository<Certification, Long> {

}
