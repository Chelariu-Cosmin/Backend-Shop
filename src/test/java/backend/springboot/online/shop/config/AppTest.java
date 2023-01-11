package backend.springboot.online.shop.config;

import backend.springboot.online.shop.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {

    // Junit test for Spring Boot main class.
    @Mock
    private SpringApplicationBuilder springApplicationBuilder;

    @Test
    public void testConfigure() {
        Application app = new Application ();

        Mockito.when(springApplicationBuilder.sources(Application.class)).thenReturn(springApplicationBuilder);

        SpringApplicationBuilder result = app.configure(springApplicationBuilder);

        Mockito.verify(springApplicationBuilder).sources(Application.class);

        assertEquals(springApplicationBuilder, result);
    }

}
