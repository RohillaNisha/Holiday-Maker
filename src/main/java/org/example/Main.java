package org.example;

import org.example.menu.MenuSystem;

import javax.security.auth.callback.PasswordCallback;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();

        System.out.println(db.listOfAllUsers());

        Users users = new Users();
        Events events = new Events();
        Packages packages = new Packages();


       // System.out.println(users.getTotalNumberOfUsers());

       // users.printAllUsers();
        events.printAllEvents();
        packages.printAllPackages();

        System.out.println("Number of packages " + packages.getTotalNumberOfPackages());
        db.listOfAllPackages().forEach(System.out::println);


        db.listOfAllRooms().forEach(System.out::println);



        MenuSystem menuSystem = MenuSystem.getInstance();
        while (true) {
            menuSystem.execute();
        }

    }
}