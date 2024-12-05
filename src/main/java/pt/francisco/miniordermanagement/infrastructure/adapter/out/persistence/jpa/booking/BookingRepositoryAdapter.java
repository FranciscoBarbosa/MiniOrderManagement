package pt.francisco.miniordermanagement.infrastructure.adapter.out.persistence.jpa.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.francisco.miniordermanagement.core.domain.booking.Booking;
import pt.francisco.miniordermanagement.core.domain.booking.BookingRepository;

@Component
@RequiredArgsConstructor
@Transactional
public class BookingRepositoryAdapter implements BookingRepository {
  private final BookingJpaRepository bookingJpaRepository;
  private final BookingEntityMapper bookingMapper;

  @Override
  public Booking save(Booking booking) {
    return bookingMapper.toDomainEntity(
        bookingJpaRepository.save(bookingMapper.toDbEntity(booking)));
  }
}
