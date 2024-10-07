package pt.francisco.miniordermanagement.adapter.out.order.persistence.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends CrudRepository<OrderEntity, Long> {

}
