package com.effigo.POC.Repository;

import com.effigo.POC.Model.FinancialDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialRepo extends JpaRepository<FinancialDetails, Long> {
}
