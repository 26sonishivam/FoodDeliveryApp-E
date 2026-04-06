package edu.classproject.auth;

import java.time.Instant;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// dependency on user module (as per requirement)
import edu.classproject.user.UserService;

public class InMemoryAuthService implements AuthService {

    private Map<String, AuthSession> sessions = new HashMap<>();

    // dependency (may be null for now)
    private UserService userService;

    public InMemoryAuthService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public AuthSession login(String email, String password) {

        // TODO: Replace with real validation using UserService
        String userId = email;

        String sessionId = UUID.randomUUID().toString();

        AuthSession session = new AuthSession(
                sessionId,
                userId,
                Instant.now(),
                Instant.now().plus(Duration.ofHours(2))
        );

        sessions.put(sessionId, session);

        return session;
    }

    @Override
    public void logout(String sessionId) {
        sessions.remove(sessionId);
    }

    @Override
    public boolean isSessionActive(String sessionId) {

        AuthSession session = sessions.get(sessionId);

        if (session == null)
            return false;

        return session.expiresAt().isAfter(Instant.now());
    }
}