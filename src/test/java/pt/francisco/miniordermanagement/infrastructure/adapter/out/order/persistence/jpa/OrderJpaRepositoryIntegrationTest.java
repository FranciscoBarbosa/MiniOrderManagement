package pt.francisco.miniordermanagement.infrastructure.adapter.out.order.persistence.jpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import pt.francisco.miniordermanagement.core.domain.order.OrderTestData;

import java.util.UUID;

@SpringBootTest
public class OrderJpaRepositoryIntegrationTest {

    @Autowired
    private OrderJpaRepository orderJpaRepository;
    @Autowired
    private OrderEntityMapper orderEntityMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private OrderRepositoryAdapter orderRepositoryAdapter;

    @BeforeEach
    void setUp(){
        orderRepositoryAdapter = new OrderRepositoryAdapter(orderJpaRepository, orderEntityMapper);
    }
    @Test
    void shouldStoreOrder() {
        var order = OrderTestData.createDefaultDomainOrder();

        orderRepositoryAdapter.save(order);

        var numberOfOrdersStored = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM order_entity", Integer.class);
        Assertions.assertThat(numberOfOrdersStored).isEqualTo(1);
    }

    @Test
    void shouldNotStoreDuplicateOrders() {
        var order = OrderTestData.createDefaultDomainOrder();

        orderRepositoryAdapter.save(order);
        orderRepositoryAdapter.save(order);

        var numberOfOrdersStored = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM order_entity", Integer.class);
        Assertions.assertThat(numberOfOrdersStored).isEqualTo(1);
    }
}
