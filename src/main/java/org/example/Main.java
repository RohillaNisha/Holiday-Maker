package org.example;

import org.example.Menu.MenuSystem;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();

        System.out.println(db.listOfAllUsers());

        Users users = new Users();

        System.out.println(users.getTotalNumberOfUsers());

        users.printAllUsers();
        db.listOfAllRooms().forEach(System.out::println);
        MenuSystem menuSystem = MenuSystem.getInstance();
        while (true) {
            menuSystem.execute();
        }

    }
}