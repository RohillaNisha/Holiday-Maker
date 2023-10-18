package org.example;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private Database db;
    private ArrayList<User> list;

    public Users() {
        this.db = new Database();
        this.list = db.listOfAllUsers();
    }

    public int getTotalNumberOfUsers(){
        return this.list.size();
    }

    public String getFirstUsersFirstName(){
        return this.list.get(0).getFirstName();
    }

    public String getLastUsersFirstName(){
        return this.list.get(this.list.size() - 1).getFirstName();
    }

    public void createUser(String firstName, String lastName, String email){
        db.createNewUser(firstName, lastName, email);
        this.list = db.listOfAllUsers();
    }

    public void deleteLastUser(){
        db.deleteLastAddedUser();
        this.list = db.listOfAllUsers();
    }
    public void printAllUsers(){
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(this.list.get(i).toString());
        }
    }
}
