package app.wordyourself.trainingssm.service;

import app.wordyourself.trainingssm.domain.Payment;
import app.wordyourself.trainingssm.domain.PaymentEvent;
import app.wordyourself.trainingssm.domain.PaymentState;
import app.wordyourself.trainingssm.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static app.wordyourself.trainingssm.domain.PaymentState.PRE_AUTH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    Payment payment;

    @BeforeEach
    void setUp() {
        payment = Payment.builder().amount(new BigDecimal("12.99")).build();
    }

    @Transactional
    @Test
    void preAuth() {
        Payment savedPayment = paymentService.newPayment(payment);

        log.debug("Should be NEW");
        log.debug(savedPayment.getState().toString());

        StateMachine<PaymentState, PaymentEvent> sm = paymentService.preAuth(savedPayment.getId());

        Payment preAuthedPayment = paymentRepository.getOne(savedPayment.getId());

        log.debug("Should be PRE_AUTH");
        log.debug(String.valueOf(sm.getState().getId()));
        //dogru duzgun yazalim elemana uymayalım.
        assertThat(sm.getState().getId()).isEqualTo(PRE_AUTH);

        log.debug(String.valueOf(preAuthedPayment));
    }
}