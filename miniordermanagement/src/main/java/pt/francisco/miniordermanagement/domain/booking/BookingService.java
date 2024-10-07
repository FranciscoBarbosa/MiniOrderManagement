package pt.francisco.miniordermanagement.domain.booking;

import lombok.RequiredArgsConstructor;
import pt.francisco.miniordermanagement.domain.booking.Booking;
import pt.francisco.miniordermanagement.domain.order.OrderNotFoundException;
import pt.francisco.miniordermanagement.domain.order.OrderRepository;

import java.util.UUID;

@RequiredArgsConstructor
public class BookingService {

    private final OrderRepository orderRepository;

    public Booking createBookingForOrderWithId(UUID orderId, String customerCode, String supplierCode, String factoryCode){
        var order = orderRepository.findOrderByOrderId(orderId);
        if(order == null){
            throw new OrderNotFoundException();
        }
        return Booking.builder()
                        .orderBookingNumber(UUID.randomUUID())
                        .customerCode(customerCode)
                        .supplierCode(supplierCode)
                        .factoryCode(factoryCode)
                        .order(order)
                        .build();
    }

}
