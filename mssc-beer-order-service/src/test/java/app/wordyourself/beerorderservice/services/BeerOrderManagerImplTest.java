package app.wordyourself.beerorderservice.services;

import app.wordyourself.beerorderservice.domain.BeerOrder;
import app.wordyourself.beerorderservice.repositories.BeerOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static app.wordyourself.beerorderservice.domain.BeerOrderStatusEnum.NEW;
import static app.wordyourself.beerorderservice.domain.BeerOrderStatusEnum.VALIDATED;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class BeerOrderManagerImplTest {

    @Autowired
    BeerOrderManager manager;

    @Autowired
    BeerOrderRepository beerOrderRepository;

    BeerOrder order;

    @BeforeEach
    void setUp() {
        order = BeerOrder.builder().customerRef("Ref").build();
    }

    @Transactional
    @Test
    void newBeerOrder() {
        BeerOrder savedOrder = manager.newBeerOrder(order);
        assertThat(savedOrder.getOrderStatus()).isEqualTo(NEW);
        log.debug("STATUS 1:" + savedOrder.getOrderStatus());

        //TODO will be changed in action, this is for test purpose only
        manager.validated(savedOrder);
        assertThat(savedOrder.getOrderStatus()).isEqualTo(VALIDATED);
        log.debug("STATUS 2:" + savedOrder.getOrderStatus());

        BeerOrder order = beerOrderRepository.getOne(savedOrder.getId());
        log.debug("STATUS 3:" + order.getOrderStatus());
        assertThat(order.getOrderStatus()).isEqualTo(VALIDATED);
    }
}