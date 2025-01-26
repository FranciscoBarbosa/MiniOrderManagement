package pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.booking;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingJpaRepository extends CrudRepository<BookingEntity, String> {
}
