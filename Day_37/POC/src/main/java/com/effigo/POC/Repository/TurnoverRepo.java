package com.effigo.POC.Repository;

import com.effigo.POC.Model.Turnover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoverRepo extends JpaRepository<Turnover, Long> {
}
