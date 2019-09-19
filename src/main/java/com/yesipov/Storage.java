package com.yesipov;

import com.yesipov.model.User;

import java.io.IOException;
import java.util.List;

public interface Storage {
    void removeAll();
    void removeUser(int id) throws IOException;
    void removeUserByName(String name) throws IOException;
    void addUser(User user) throws IOException;
    void updateUser(User user,User newUser) throws IOException;
    User getUser(int id) throws IOException;
    List<User> getAllUsers() throws IOException;
}