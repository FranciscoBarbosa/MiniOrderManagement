package pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends CrudRepository<OrderEntity, String> {}
