package app.wordyourself.trainingssm.service;

import app.wordyourself.trainingssm.domain.Payment;
import app.wordyourself.trainingssm.domain.PaymentEvent;
import app.wordyourself.trainingssm.domain.PaymentState;
import app.wordyourself.trainingssm.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * alper - 11/08/2020
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    public static final String PAYMENT_ID_HEADER = "payment_id";
    private final PaymentRepository repo;
    private final StateMachineFactory<PaymentState, PaymentEvent> factory;
    private final PaymentStateChangeInterceptor stateChangeInterceptor;

    @Transactional
    @Override
    public Payment newPayment(Payment payment){
        payment.setState(PaymentState.NEW);
        return repo.save(payment);
    }

    @Transactional
    @Override
    public StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId) {
        StateMachine<PaymentState, PaymentEvent> sm = build(paymentId);
        //action ile karar verilecek APPROVE kismina StateMachineConfig.preAuthAction
        sendEvent(paymentId, sm, PaymentEvent.PRE_AUTHORIZE);

        return sm;
    }

    @Transactional
    @Override
    public StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId) {
        StateMachine<PaymentState, PaymentEvent> sm = build(paymentId);

        sendEvent(paymentId, sm, PaymentEvent.AUTH_APPROVED);

        return sm;
    }

    @Transactional
    @Override
    public StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId) {
        StateMachine<PaymentState, PaymentEvent> sm = build(paymentId);

        sendEvent(paymentId, sm, PaymentEvent.AUTH_DECLINED);

        return sm;
    }

    private void sendEvent(Long paymentId, StateMachine<PaymentState, PaymentEvent> sm, PaymentEvent event){
        Message msg = MessageBuilder.withPayload(event)
                            .setHeader(PAYMENT_ID_HEADER, paymentId)
                            .build();

        sm.sendEvent(msg);
    }


    /**
     * @param paymentId
     * @return
     */
    private StateMachine<PaymentState, PaymentEvent> build(Long paymentId) {
        //niye db'den cektik, niye stop edip bastan baslattik?
        Payment payment = repo.getOne(paymentId);
        String id = Long.toString(payment.getId());
        StateMachine<PaymentState, PaymentEvent> sm = factory.getStateMachine(id);
        log.debug("STATE: " +sm.getState());
        sm.stop();
        log.debug("STATE: (stop) " +sm.getState());

        //bu kisim config'de olamaz miydi?
        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(stateChangeInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(payment.getState(), null, null, null));
                });

        sm.start();
        log.debug("STATE: (start)" +sm.getState());
        return sm;
    }
}
