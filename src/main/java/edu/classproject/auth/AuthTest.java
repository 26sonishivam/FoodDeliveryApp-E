package edu.classproject.auth;

public class AuthTest {

    public static void main(String[] args) {

        AuthService auth = new InMemoryAuthService(null);

        AuthSession session = auth.login("test@mail.com", "123");

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