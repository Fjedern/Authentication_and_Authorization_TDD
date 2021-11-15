package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginHandlerTest {

    @Test
    void create_new_login_success() {
        LoginHandler loginHandler = new LoginHandler();

        Login login = loginHandler.startLogin("anna", "losen");

        assertNotNull(login);
        assertEquals("anna", login.getUsername());
        assertEquals("losen", login.getPassword());
    }
}
