package pt.francisco.miniordermanagement.domain.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.francisco.miniordermanagement.domain.entities.Booking;
import pt.francisco.miniordermanagement.domain.entities.Order;
import pt.francisco.miniordermanagement.domain.exceptions.OrderNotFoundException;
import pt.francisco.miniordermanagement.domain.repositories.OrderRepository;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    @Mock
    private OrderRepository orderRepository;
    private BookingService bookingService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        bookingService = new BookingService(orderRepository);
    }

    @Test
    void shouldCreateBookingForExistentOrder(){
        var orderId = UUID.randomUUID();
        var order = Order.builder().orderId(orderId).customerCode("testCustomer").build();
        when(orderRepository.findOrderByOrderId(orderId)).thenReturn(order);

        Booking booking = bookingService.createBookingForOrderWithId(orderId, "testCustomer", "testSupplier", "testFactory");

        Assertions.assertEquals(orderId, booking.order().orderId());
    }

    @Test
    void shouldThrowExceptionWhenCreatingBookingForInexistentOrder(){
        var orderId = UUID.randomUUID();
        when(orderRepository.findOrderByOrderId(orderId)).thenReturn(null);

        Assertions.assertThrows(OrderNotFoundException.class, () -> {
            bookingService.createBookingForOrderWithId(orderId, "testCustomer", "testSupplier", "testFactory");
        });
    }

}