package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoginHandlerTest {
    LoginHandler loginHandler;

    @BeforeEach
    void setUp() {
        loginHandler = new LoginHandler();
        loginHandler.addUser("anna", "losen");
        loginHandler.addUser("berit", "123456");
        loginHandler.addUser("kalle", "password");

        loginHandler.addPermissionsToResourceForUser("anna", "ACCOUNT", List.of(Permissions.READ));
        loginHandler.addPermissionsToResourceForUser("berit", "ACCOUNT", List.of(Permissions.READ, Permissions.WRITE));
        loginHandler.addPermissionsToResourceForUser("anna", "PROVISION_CALC", List.of(Permissions.EXECUTE));
    }

    @Test
    void test_login_user_return_token_success() throws WrongLoginException {
        String isLoggedIn = loginHandler.startLogin("anna", "losen");

        assertEquals(loginHandler.userList.get("anna").getToken(), isLoggedIn);
    }

    @Test
    void test_login_user_failed_because_wrong_password() {
        WrongLoginException wrongLoginException = assertThrows(WrongLoginException.class, () -> loginHandler.startLogin("anna", "apa"));

        assertThrows(WrongLoginException.class, () -> loginHandler.startLogin("anna", "apa"));
        assertEquals("Wrong password", wrongLoginException.getMessage());
    }

    @Test
    void test_login_user_failed_because_null_username() {
        WrongLoginException wrongLoginException = assertThrows(WrongLoginException.class, () -> loginHandler.startLogin(null, "apa"));

        assertEquals("Wrong username", wrongLoginException.getMessage());
    }

    @Test
    void test_check_if_token_is_valid_success() throws WrongLoginException {
        String token = loginHandler.startLogin("anna", "losen");
        boolean isTokenValid = loginHandler.validateToken(token);

        assertTrue(isTokenValid);
    }

    @Test
    void test_check_user_permissions_success() throws WrongLoginException {
        String token = loginHandler.startLogin("berit", "123456");
        List<Permissions> testList = List.of(Permissions.READ, Permissions.WRITE);

        assertEquals(testList , loginHandler.getPermission(token, "ACCOUNT"));
    }
}

