package pt.francisco.miniordermanagement.core.domain.booking;

public class OrderAlreadyBookedException extends RuntimeException {
	OrderAlreadyBookedException(final String message) {
		super(message);
	}
}
