package app.wordyourself.trainingssm.repository;

import app.wordyourself.trainingssm.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * alper - 11/08/2020
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
