package edu.classproject.auth;

public interface AuthService {

    AuthSession signup(String email, String password);

    AuthSession login(String email, String password);

    void logout(String sessionId);

    boolean isSessionActive(String sessionId);
}