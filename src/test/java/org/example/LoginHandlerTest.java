package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginHandlerTest {
    LoginHandler loginHandler;

    @BeforeEach
    void setUp() {
        loginHandler = new LoginHandler();
        loginHandler.addUser("anna", "losen");
        loginHandler.addUser("berit", "123456");
        loginHandler.addUser("kalle", "password");
    }

    @Test
    void login_user_success() {
        int isLoggedIn = LoginHandler.startLogin("anna", "losen");

        assertEquals(LoginHandler.hashedUserList.get("anna").getToken(), isLoggedIn);
    }
}

