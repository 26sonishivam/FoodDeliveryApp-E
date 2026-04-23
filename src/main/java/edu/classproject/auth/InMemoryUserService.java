package edu.classproject.user;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserService implements UserService {

    private Map<String, String> users = new HashMap<>();

    @Override
    public void signup(String email, String password) {
        if (users.containsKey(email)) {
            throw new RuntimeException("User already exists");
        }
        users.put(email, password);
    }

    @Override
    public boolean validate(String email, String password) {
        return users.containsKey(email) &&
               users.get(email).equals(password);
    }
}