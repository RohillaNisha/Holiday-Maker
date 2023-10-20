package org.example;

import org.example.Menu.MenuSystem;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        Users users = new Users();
        Events events=new Events();
        MenuSystem menuSystem = MenuSystem.getInstance();
        while (true) {
            menuSystem.execute();
        }

    }
}