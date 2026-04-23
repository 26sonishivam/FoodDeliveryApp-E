package edu.classproject.auth;

import edu.classproject.user.InMemoryUserService;
import edu.classproject.user.UserService;

public class AuthDemo {

    public static void main(String[] args) {

        UserService userService = new InMemoryUserService();
        AuthService authService = new InMemoryAuthService(userService);

        // SIGNUP
        AuthSession session = authService.signup("user@gmail.com", "1234");
        System.out.println("Signed up & logged in. Session ID: " + session.sessionId());

        // LOGIN
        AuthSession loginSession = authService.login("user@gmail.com", "1234");
        System.out.println("Logged in again. Session ID: " + loginSession.sessionId());

        // CHECK
        System.out.println("Session active? " +
                authService.isSessionActive(loginSession.sessionId()));

        // LOGOUT
        authService.logout(loginSession.sessionId());
        System.out.println("Logged out.");

        // CHECK AGAIN
        System.out.println("Session active after logout? " +
                authService.isSessionActive(loginSession.sessionId()));
    }
}