package com.SpringBatch.PaymentAuth.Learn.Repository;

import com.SpringBatch.PaymentAuth.Learn.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
