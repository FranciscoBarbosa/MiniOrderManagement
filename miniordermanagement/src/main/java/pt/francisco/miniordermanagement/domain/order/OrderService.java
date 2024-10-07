package pt.francisco.miniordermanagement.domain.order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order createOrder(Order order){
        if(orderAlreadyExists(order)){
            throw new OrderAlreadyExistsException();
        }
        return orderRepository.save(order);
    }

    private boolean orderAlreadyExists(Order order){
        return orderRepository.findOrderByOrderId(order.orderId()) != null;
    }
}
