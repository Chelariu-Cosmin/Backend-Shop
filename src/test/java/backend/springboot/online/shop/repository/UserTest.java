package backend.springboot.online.shop.repository;

import backend.springboot.online.shop.model.Role;
import backend.springboot.online.shop.model.User;
import backend.springboot.online.shop.repository.RoleRepository;
import backend.springboot.online.shop.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Mock
    private TestEntityManager entityManager;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testAddRoleToNewUser() {

        User user = new User ();
        user.setEmail ("chelariu.gates@gmail.com");
        user.setPassword ("mike2020");
        user.setFirstName ("Mikea");
        user.setLastName ("Gatesa");
        user.setRoles (List.of (new Role ("ROLE_USER")));

        User savedUser = userRepository.save (user);

        assertThat (savedUser.getRoles ()
                             .size ()).isEqualTo (1);
    }

    @Test
    void testCreateScrumUsers() {
        // clean-up first
      //  userRepository.deleteAll ();

        BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder ();
        // recreate users
        userRepository.save (new User ("Matei", "Codin", "mateo@yahoo.yu", passEncoder.encode ("msd"), List.of (new Role ("ROLE_USER"))));

        // check users
        assertTrue (userRepository.findAll ()
                                  .size () > 0, "Users not created!");
        userRepository.findAll ()
                      .forEach (u -> System.out.println ("User created:" +
                              u.getId () + "_" + u.getEmail () + "_"
                              + u.getPassword () + "_" + u.getRoles ()));
    }

    @Test
    public void testFindAll() {
        User users = getUser ();
        userRepository.save (users);
        List<User> result = new ArrayList<> (userRepository.findAll ());
        assertEquals (result.size (), 2);
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName ("mahesh");
        user.setLastName ("Mateo");
        user.setEmail ("mahesh@yahoo.com");
        user.setPassword ("Some");
        user.setRoles (List.of (new Role ("USER_ROLE")));
        return user;
    }
}
