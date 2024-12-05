package pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import pt.francisco.miniordermanagement.core.domain.order.OrderTestData;

@SpringBootTest
class OrderJpaRepositoryIntegrationTest {
  @Autowired private JdbcTemplate jdbcTemplate;
  @Autowired private OrderRepositoryAdapter orderRepositoryAdapter;

  @Test
  void shouldStoreOrder() {
    var order = OrderTestData.createDefaultDomainOrder();

    orderRepositoryAdapter.save(order);

    var numberOfOrdersStored =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM order_entity", Integer.class);
    Assertions.assertThat(numberOfOrdersStored).isEqualTo(1);
  }

  @Test
  void shouldNotStoreDuplicateOrders() {
    var order = OrderTestData.createDefaultDomainOrder();

    orderRepositoryAdapter.save(order);
    orderRepositoryAdapter.save(order);

    var numberOfOrdersStored =
        jdbcTemplate.queryForObject("SELECT COUNT(*) FROM order_entity", Integer.class);
    Assertions.assertThat(numberOfOrdersStored).isEqualTo(1);
  }

  @Test
  void shouldFindOrderById() {
    var order = OrderTestData.createDefaultDomainOrder();
    orderRepositoryAdapter.save(
        order); // TODO: replace by a sql script to populate initial data or something similar

    var foundOrder = orderRepositoryAdapter.findOrderByOrderId(order.getOrderId()).get();

    Assertions.assertThat(foundOrder.getOrderId()).isEqualTo(order.getOrderId());
    Assertions.assertThat(foundOrder.getOrderDate()).isEqualTo(order.getOrderDate());
    Assertions.assertThat(foundOrder.getOrderlineList().size())
        .isEqualTo(order.getOrderlineList().size());
    Assertions.assertThat(foundOrder.getCustomerCode()).isEqualTo(order.getCustomerCode());
    Assertions.assertThat(foundOrder.getOrderBookingNumber())
        .isEqualTo(order.getOrderBookingNumber());
  }
}
