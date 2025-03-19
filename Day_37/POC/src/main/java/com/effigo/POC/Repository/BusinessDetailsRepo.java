package com.effigo.POC.Repository;

import com.effigo.POC.Model.BusinessDetails;
import com.effigo.POC.Model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessDetailsRepo extends JpaRepository<BusinessDetails, Long> {

}
