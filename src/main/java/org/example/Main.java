package org.example;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();

        System.out.println(db.listOfAllUsers());

        Users users = new Users();

        System.out.println(users.getTotalNumberOfUsers());

        users.printAllUsers();
        db.listOfAllRooms().forEach(System.out::println);

    }
}