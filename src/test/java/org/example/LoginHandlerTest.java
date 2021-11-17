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
    void test_login_user_return_token_success() throws WrongLoginException {
        String isLoggedIn = loginHandler.startLogin("anna", "losen");

        assertEquals(loginHandler.userList.get("anna").getToken(), isLoggedIn);
    }

    @Test
    void test_login_user_failed_because_wrong_password() {
        WrongLoginException wrongLoginException = assertThrows(WrongLoginException.class, () -> loginHandler.startLogin("anna", "apa"));

        assertEquals("Wrong login", wrongLoginException.getMessage());
    }

    @Test
    void test_check_if_token_is_valid_success() throws WrongLoginException {
        String token = loginHandler.startLogin("anna", "losen");
        boolean isTokenValid = loginHandler.validateToken(token);

        assertTrue(isTokenValid);
    }
}

