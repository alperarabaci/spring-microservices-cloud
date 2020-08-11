package app.wordyourself.trainingssm.config;

import app.wordyourself.trainingssm.domain.PaymentEvent;
import app.wordyourself.trainingssm.domain.PaymentState;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

@SpringBootTest
@Slf4j
class StateMachineConfigTest {

    @Autowired
    StateMachineFactory<PaymentState, PaymentEvent> factory;

    @Test
    void testNewStateMachine() {
        StateMachine<PaymentState, PaymentEvent> sm = factory.getStateMachine(UUID.randomUUID());
        sm.start();
        log.debug("TEST Start:" +sm.getState().toString());

        sm.sendEvent(PaymentEvent.PRE_AUTHORIZE);
        log.debug("TEST 1:" +sm.getState().toString());

        sm.sendEvent(PaymentEvent.PRE_AUTH_APPROVED);
        log.debug("TEST 2:" +sm.getState().toString());
    }

    @Test
    void testNewStateMachine2() {
        StateMachine<PaymentState, PaymentEvent> sm = factory.getStateMachine(UUID.randomUUID());
        sm.start();
        log.debug("TEST2 Start:" +sm.getState().toString());

        sm.sendEvent(PaymentEvent.PRE_AUTHORIZE);
        log.debug("TEST2 1:" +sm.getState().toString());

        sm.sendEvent(PaymentEvent.PRE_AUTH_DECLINED);
        log.debug("TEST2 3:" +sm.getState().toString());
    }
}