package edu.classproject.user;

public interface UserService {
    void signup(String email, String password);
    boolean validate(String email, String password);
}