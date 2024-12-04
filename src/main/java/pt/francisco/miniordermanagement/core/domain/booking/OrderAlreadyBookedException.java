package pt.francisco.miniordermanagement.core.domain.booking;

public class OrderAlreadyBookedException extends RuntimeException {
  OrderAlreadyBookedException(String message) {
    super(message);
  }
}
