package com.yesipov;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yesipov.model.User;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileStorage implements Storage {
    private File file;

    public FileStorage(File file) {
        this.file = file;
    }

    public FileStorage() {
    }

    public ArrayList<User> listFromFile(File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User[] users;
        users = gson.fromJson(FileUtils.readFileToString(file), User[].class);
        return new ArrayList<User>(Arrays.asList(users));
    }

    public void listToFile(List<User> users, File file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileUtils.writeStringToFile(file, gson.toJson(users));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) throws IOException {
        List<User> users = new ArrayList<>();
        if (file.length() != 0) {
            users = listFromFile(file);
        }
        users.add(user);
        listToFile(users, file);
    }

    @Override
    public void removeAll() {
        try {
            FileUtils.write(new File(file.getAbsolutePath()), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUser(int id) throws IOException {
        List<User> users = listFromFile(file);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
            }
        }
        listToFile(users, file);
    }

    @Override
    public void removeUserByName(String name) throws IOException {
        List<User> users = listFromFile(file);
        for (User u : users) {
            if (u.getName().equals(name)) {
                users.remove(u);
            }
        }
        listToFile(users, file);
    }

    @Override
    public void updateUser(User user, User newUser) throws IOException {
        List<User> users = listFromFile(file);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(newUser)) {
                users.set(i,newUser);
            }
        }
        listToFile(users, file);
    }

    @Override
    public User getUser(int id) throws IOException {
        List<User> users = listFromFile(file);
        for (User user : users) {
            if (user.getId() == id) {
                return users.get(users.indexOf(user));
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        return listFromFile(file);
    }

    @Override
    public String toString() {
        try {
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

