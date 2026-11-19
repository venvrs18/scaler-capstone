package project.scaler.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.scaler.capstone.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}