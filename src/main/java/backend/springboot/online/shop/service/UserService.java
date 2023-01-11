package backend.springboot.online.shop.service;

import backend.springboot.online.shop.dto.UserRegistrationDto;
import backend.springboot.online.shop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);

    List<User> getAll();

    Optional<User> find(Long id);

    void delete(Long id);

}
