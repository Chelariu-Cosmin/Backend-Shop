package backend.springboot.online.shop.repository;

import backend.springboot.online.shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
