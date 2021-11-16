package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginHandlerTest {
    LoginHandler loginHandler;

    @BeforeEach
    void setUp() {
        loginHandler = new LoginHandler();
        loginHandler.addAllUsers();
    }

    @Test
    void create_new_login_success() {

        boolean isLoggedIn = LoginHandler.startLogin("anna", "losen");

        assertTrue(isLoggedIn);
    }
}
