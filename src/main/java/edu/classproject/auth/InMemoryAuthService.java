package edu.classproject.auth;

import java.time.Instant;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import edu.classproject.user.UserService;

public class InMemoryAuthService implements AuthService {

    private Map<String, AuthSession> sessions = new HashMap<>();
    private UserService userService;

    public InMemoryAuthService(UserService userService) {
        this.userService = userService;
    }

    // ✅ SIGNUP
    @Override
    public AuthSession signup(String email, String password) {
        userService.signup(email, password);
        return login(email, password); // auto-login
    }

    // ✅ LOGIN
    @Override
    public AuthSession login(String email, String password) {

        if (!userService.validate(email, password)) {
            throw new RuntimeException("Invalid credentials");
        }

        String sessionId = UUID.randomUUID().toString();

        AuthSession session = new AuthSession(
                sessionId,
                email,
                Instant.now(),
                Instant.now().plus(Duration.ofHours(2))
        );

        sessions.put(sessionId, session);

        return session;
    }

    // ✅ LOGOUT
    @Override
    public void logout(String sessionId) {
        sessions.remove(sessionId);
    }

    // ✅ SESSION CHECK
    @Override
    public boolean isSessionActive(String sessionId) {
        AuthSession session = sessions.get(sessionId);

        if (session == null) return false;

        return session.expiresAt().isAfter(Instant.now());
    }
}