package backend.springboot.online.shop.service;

import backend.springboot.online.shop.model.OrderDetails;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsService {

    OrderDetails saveOrderDetails(OrderDetails orderDetails);

    List<OrderDetails> findAllOrderDetails();

    Optional<OrderDetails> findOrderDetails(Long id);
}
