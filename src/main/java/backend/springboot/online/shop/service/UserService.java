package backend.springboot.online.shop.service;

import backend.springboot.online.shop.dto.UserRegistrationDto;
import backend.springboot.online.shop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);

    List<User> getAll();

    User find(Long id);

    void delete(Long id);

    Long addUser(User user);

}
