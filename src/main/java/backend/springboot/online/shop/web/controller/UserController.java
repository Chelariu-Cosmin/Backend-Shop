package backend.springboot.online.shop.web.controller;

import backend.springboot.online.shop.model.Article;
import backend.springboot.online.shop.model.User;
import backend.springboot.online.shop.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

//@Controller
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;



    public UserController(UserService userService) {
        super ();
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Optional<User> find(@PathVariable("id") @NotNull Long id) {
        try {
            return userService.find (id);
        } catch (UsernameNotFoundException NotFound) {
            throw new UsernameNotFoundException ("This user does not exist!");
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

        @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAll ();
    }
}
