package edu.classproject.auth;

import edu.classproject.user.InMemoryUserService;
import edu.classproject.user.UserService;

public class AuthTest {

    public static void main(String[] args) {

        UserService userService = new InMemoryUserService();
        AuthService auth = new InMemoryAuthService(userService);

        AuthSession session = auth.signup("test@mail.com", "123");

        if (!auth.isSessionActive(session.sessionId())) {
            throw new RuntimeException("Test failed: session should be active");
        }

        auth.logout(session.sessionId());

        if (auth.isSessionActive(session.sessionId())) {
            throw new RuntimeException("Test failed: session should be inactive");
        }

        System.out.println("All tests passed");
    }
}