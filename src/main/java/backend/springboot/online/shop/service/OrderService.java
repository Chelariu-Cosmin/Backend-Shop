package backend.springboot.online.shop.service;

import backend.springboot.online.shop.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();

    Optional<Order> find(Long id);

    Long create(Order order);

    void delete(Long id);
}
