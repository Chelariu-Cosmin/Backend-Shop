package backend.springboot.online.shop.rest;

import backend.springboot.online.shop.model.Role;
import backend.springboot.online.shop.model.User;
import backend.springboot.online.shop.web.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
@SpringBootTest
public class UserControllerTest {
    //delete by id
    //find/findAll
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserController userController;

    @Test
    public void getUsers() throws Exception {
        User user = new User();
        user.setRoles (List.of (new Role ("ROLE_USER")));

        List<User> allUsers = singletonList(user);

        given(userController.getAllUsers ()).willReturn(allUsers);

        mvc.perform(get(allUsers + "all")
                   .with(user("a").password("Q1w2e3r4"))
                   .contentType(APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(2)));
    }
}
