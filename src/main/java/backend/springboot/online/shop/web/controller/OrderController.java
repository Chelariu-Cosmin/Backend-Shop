package backend.springboot.online.shop.web.controller;

import backend.springboot.online.shop.model.Order;
import backend.springboot.online.shop.service.OrderService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderServices;

    public OrderController(OrderService orderServices) {
        this.orderServices = orderServices;
    }

    @GetMapping("/")
    public List<Order> find() {
        return orderServices.findAll ();
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> delete(@PathVariable("id") @NotNull Long id) {
        try {
            orderServices.delete (id);
            return new ResponseEntity<> (HttpStatus.OK);
        } catch (InvalidDataAccessApiUsageException invalidData) {
            throw new ResponseStatusException (HttpStatus.BAD_REQUEST, invalidData.getMessage ());
        } catch (Throwable throwable) {
            throw new ResponseStatusException (HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage ());
        }
    }

    @PostMapping
    public ResponseEntity<Long> Order(@RequestBody Order order) {
        try {
            return new ResponseEntity<> (orderServices.create (order), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException data) {
            throw new ResponseStatusException (HttpStatus.BAD_REQUEST, data.getMessage ());
        } catch (ResponseStatusException exception) {
            throw new ResponseStatusException (HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage ());
        }
    }
}
