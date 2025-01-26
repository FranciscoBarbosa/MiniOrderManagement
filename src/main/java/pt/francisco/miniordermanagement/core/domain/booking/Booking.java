package pt.francisco.miniordermanagement.core.domain.booking;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import pt.francisco.miniordermanagement.core.domain.order.Order;

@Builder
public record Booking(OrderBookingNumber orderBookingNumber, LocalDateTime bookingDate, Order order) {

    public Booking(final OrderBookingNumber orderBookingNumber, final LocalDateTime bookingDate, final Order order) {
        this.orderBookingNumber = orderBookingNumber;
        this.bookingDate = bookingDate;
        this.order = new Order(order.getOrderId(), order.getCustomerCode(), order.getOrderDate(), List.copyOf(order.getOrderlineList()));
    }

    @Override
    public Order order(){
        return new Order(order.getOrderId(), order.getCustomerCode(), order.getOrderDate(), List.copyOf(order.getOrderlineList()));
    }

    public static class BookingBuilder {
        private Order order;

        public BookingBuilder order(final Order order) {
            this.order = new Order(order.getOrderId(), order.getCustomerCode(), order.getOrderDate(), List.copyOf(order.getOrderlineList()));
            return this;
        }
    }}
// TODO: add the booking status (In_Progress, Confirmed, Cancelled)...
