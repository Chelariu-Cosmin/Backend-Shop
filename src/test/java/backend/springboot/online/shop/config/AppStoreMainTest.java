package backend.springboot.online.shop.config;

import backend.springboot.online.shop.Application;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppStoreMainTest {

        @Test
        public void testMain() {
            Application.main(new String[] {});
            assertTrue(true);
        }
    }
