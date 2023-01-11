package backend.springboot.online.shop.service.impl;

import backend.springboot.online.shop.dto.UserRegistrationDto;
import backend.springboot.online.shop.model.Role;
import backend.springboot.online.shop.model.User;
import backend.springboot.online.shop.repository.UserRepository;
import backend.springboot.online.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {

        User user = new User (registrationDto.getFirstName (), registrationDto.getLastName (), registrationDto.getEmail (),
                passwordEncoder.encode (registrationDto.getPassword ()), Arrays.asList (new Role ("ROLE_USER")));

        return userRepository.save (user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail (username);
        if (user == null) {
            throw new UsernameNotFoundException ("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User (user.getEmail (), user.getPassword (),
                mapRolesToAuthorities (user.getRoles ()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

        return roles.stream ()
                    .map (role -> new SimpleGrantedAuthority (role.getName ()))
                    .collect (Collectors.toList ());
    }

    @Override
    public List<User> getAll() {

        return this.userRepository.findAll ();
    }

    @Override
    public Optional<User> find(Long id) {

        return userRepository.findById (id);
    }

    @Override
    public void delete(Long id) {

        userRepository.deleteById (id);
    }


}
