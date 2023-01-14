package backend.springboot.online.shop.web.controller;

import backend.springboot.online.shop.model.User;
import backend.springboot.online.shop.service.UserService;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        super ();
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User find(@PathVariable("id") @NotNull Long id) {
        try {
            return userService.find (id);
        } catch (NoSuchElementException e) {
            log.info ("Not found User");
            throw new ResponseStatusException (HttpStatus.NOT_FOUND, e.getMessage ());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @NotNull Long id) {
        try {
            userService.delete (id);
            return new ResponseEntity<> (HttpStatus.OK);
        } catch (InvalidDataAccessApiUsageException invalidData) {
            throw new ResponseStatusException (HttpStatus.BAD_REQUEST, invalidData.getMessage ());
        } catch (Throwable throwable) {
            throw new ResponseStatusException (HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage ());
        }
    }

    @PostMapping
    public ResponseEntity<Long> addUser(@Valid @RequestBody User user) {
        try {
            return new ResponseEntity<> (userService.addUser (user), HttpStatus.CREATED);
        } catch (InvalidDataAccessApiUsageException invalidData) {
            throw new ResponseStatusException (HttpStatus.BAD_REQUEST, invalidData.getMessage ());
        } catch (Throwable throwable) {
            throw new ResponseStatusException (HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage ());
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll ();
    }
}
