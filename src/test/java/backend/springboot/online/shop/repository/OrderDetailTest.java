package backend.springboot.online.shop.repository;

import backend.springboot.online.shop.model.OrderDetails;
import backend.springboot.online.shop.service.OrderDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
public class OrderDetailTest {
    @Autowired
    private OrderDetailsRepository OrderDetailsRepository;

    @Mock
    private TestEntityManager entityManager;

    @Test
    public void testFindAll() {
        OrderDetails ordersDetails = getOrderDetails ();
        OrderDetailsRepository.save (ordersDetails);
        OrderDetailsService orderDetailsService;
        List<OrderDetails> result = new ArrayList<OrderDetails> (OrderDetailsRepository.findAll ());
        assertEquals (result.size (), 3);
    }

    private OrderDetails getOrderDetails() {
        OrderDetails ordersDetails = new OrderDetails ();
        ordersDetails.setPrice (200);
        ordersDetails.setCurrency ("RON");
        ordersDetails.setMethod ("test_method");
        ordersDetails.setDescription ("test");
        ordersDetails.setIntent ("test_intent");
        return ordersDetails;
    }
}