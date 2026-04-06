package edu.classproject.auth;

public class AuthDemo {

    public static void main(String[] args) {

        AuthService authService = new InMemoryAuthService();

        // LOGIN
        AuthSession session = authService.login("user@gmail.com", "1234");
        System.out.println("Logged in. Session ID: " + session.sessionId());

        // CHECK SESSION
        boolean active = authService.isSessionActive(session.sessionId());
        System.out.println("Session active? " + active);

        // LOGOUT
        authService.logout(session.sessionId());
        System.out.println("Logged out.");

        // CHECK AGAIN
        active = authService.isSessionActive(session.sessionId());
        System.out.println("Session active after logout? " + active);
    }
}