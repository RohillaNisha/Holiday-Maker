package org.example;

import org.example.menu.MenuSystem;

import javax.security.auth.callback.PasswordCallback;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        Users users = new Users();
        Events events=new Events();
        Packages packages = new Packages();

        MenuSystem menuSystem = MenuSystem.getInstance();
        while (true) {
            menuSystem.execute();
        }

    }
}