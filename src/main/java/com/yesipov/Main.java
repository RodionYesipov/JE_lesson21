package com.yesipov;


import com.yesipov.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("./output.txt");
        FileStorage fileStorage = new FileStorage(file);


        fileStorage.addUser(new User(11, "Rodion", 26));
        fileStorage.addUser(new User(23, "Alex", 55));
        fileStorage.addUser(new User(34, "Ben", 19));

        System.out.println("Primary list of users:");
        System.out.println(fileStorage);

        int id = 11;
        System.out.println("\n\n\nGet user id = " + id);
        System.out.println(fileStorage.getUser(id));

        System.out.println("\n\n\nGet all users");
        System.out.println(fileStorage.getAllUsers());


        id = 11;
        System.out.println("\n\n\nRemove user id = " + id);
        fileStorage.removeUser(id);

        System.out.println("List after remove");
        System.out.println(fileStorage);

        String name = "Alex";
        System.out.println("\n\n\nRemove user name = " + name);
        fileStorage.removeUserByName(name);

        System.out.println("List after remove");
        System.out.println(fileStorage);

        id = 34;
        User user = new User(id, "Mike", 41);
        System.out.println("\n\n\nUpdate user id = " + fileStorage.getUser(id));
        fileStorage.updateUser(fileStorage.getUser(id), user);

        System.out.println("List after update");
        System.out.println(fileStorage);

        System.out.println("\n\n\nRemove all users");
        fileStorage.removeAll();

        System.out.println("List after remove");
        System.out.println(fileStorage);
    }
}
