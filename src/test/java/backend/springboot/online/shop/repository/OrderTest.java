package backend.springboot.online.shop.repository;

import backend.springboot.online.shop.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
public class OrderTest {

    @Autowired
    private OrderRepository orderRepository;

    @Mock
    private TestEntityManager entityManager;

    @Test
    public void testFindAll() {
        Order orders = getOrder ();
        orderRepository.save (orders);
        //OrderService orderServices;
        List<Order> result = new ArrayList<Order> (orderRepository.findAll ());
        assertEquals (result.size (), 1);
    }

    private Order getOrder() {
        Order orders = new Order ();
        orders.setId (1000L);
        orders.setCashAmount (BigDecimal.valueOf (10));
        orders.setUnits (3);
        return orders;
    }
}
