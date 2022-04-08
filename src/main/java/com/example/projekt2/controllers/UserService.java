package com.example.projekt2.controllers;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class UserService {
    private Repository repository;

    public UserService(Repository repository) {
        this.repository = repository;
    }

    public User userLogin(String username, String password) throws SQLException {
        User user = repository.getUser(username, password);

        return user;
    }


    public void createUser(String username, String password) throws SQLException {
        repository.createUser(username, password);

    }

    public boolean checkForUser(HttpSession session) {
        Boolean userExists = true;

        try {
            session.getAttribute("user");
        } catch (NullPointerException e) {
            userExists = false;
        }
        return userExists;
    }
}
