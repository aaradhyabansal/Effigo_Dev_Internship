package com.effigo.POC.Repository;

import com.effigo.POC.Model.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDetailsRepo extends JpaRepository<ContactDetails, Long> {
}
