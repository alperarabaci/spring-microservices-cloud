package app.wordyourself.trainingssm.service;

import app.wordyourself.trainingssm.domain.Payment;
import app.wordyourself.trainingssm.domain.PaymentEvent;
import app.wordyourself.trainingssm.domain.PaymentState;
import app.wordyourself.trainingssm.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

/**
 * alper - 11/08/2020
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository repo;
    private final StateMachineFactory<PaymentState, PaymentEvent> factory;

    @Override
    public Payment newPayment(Payment payment){
        payment.setState(PaymentState.NEW);
        return repo.save(payment);
    }

    @Override
    public StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId) {
        StateMachine<PaymentState, PaymentEvent> sm = build(paymentId);
        return null;
    }

    @Override
    public StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId) {
        StateMachine<PaymentState, PaymentEvent> sm = build(paymentId);
        return null;
    }

    @Override
    public StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId) {
        StateMachine<PaymentState, PaymentEvent> sm = build(paymentId);
        return null;
    }


    private StateMachine<PaymentState, PaymentEvent> build(Long paymentId) {
        //niye db'den cektik, niye stop edip bastan baslattik?
        Payment payment = repo.getOne(paymentId);
        String id = Long.toString(payment.getId());
        StateMachine<PaymentState, PaymentEvent> sm = factory.getStateMachine(id);
        log.debug("STATE: " +sm.getState().toString());
        sm.stop();
        log.debug("STATE: (stop) " +sm.getState().toString());

        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.resetStateMachine(new DefaultStateMachineContext<>(payment.getState(), null, null, null));
                });

        sm.start();
        log.debug("STATE: (start)" +sm.getState().toString());
        return sm;
    }
}
