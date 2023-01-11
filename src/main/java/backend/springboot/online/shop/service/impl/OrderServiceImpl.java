package backend.springboot.online.shop.service.impl;

import backend.springboot.online.shop.model.Order;
import backend.springboot.online.shop.model.OrderDetails;
import backend.springboot.online.shop.repository.OrderDetailsRepository;
import backend.springboot.online.shop.repository.OrderRepository;
import backend.springboot.online.shop.service.OrderDetailsService;
import backend.springboot.online.shop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService, OrderDetailsService {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orderList = new ArrayList<> ();

        orderRepository.findAll ()
                       .forEach (orderList::add);
        return orderList;
    }

    @Override
    public Optional<Order> find(Long id) {
        return orderRepository.findById (id);
    }

    @Override
    public Long create(Order order) {
        return orderRepository.save (order)
                              .getId ();
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById (id);
    }

    @Override
    public OrderDetails saveOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepository.save (orderDetails);
    }

    @Override
    public List<OrderDetails> findAllOrderDetails() {
        return orderDetailsRepository.findAll ();
    }

    @Override
    public Optional<OrderDetails> findOrderDetails(Long id) {

        return orderDetailsRepository.findById (id);
    }
}
