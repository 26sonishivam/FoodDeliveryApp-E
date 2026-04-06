package edu.classproject.auth;

import java.time.Instant;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryAuthService implements AuthService {

    private Map<String, AuthSession> sessions = new HashMap<>();

    @Override
    public AuthSession login(String email, String password) {

        // NOTE: No real password validation for now
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