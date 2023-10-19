package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Users {

    private Database db;
    private ArrayList<User> list;

    Scanner input = new Scanner(System.in);

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

    public void createUser(String firstName, String lastName, String email, String contactNumber, String personalNumber, String address, LocalDate dob){
        db.createNewUser(firstName, lastName, email, contactNumber, personalNumber, address, dob);
        this.list = db.listOfAllUsers();
    }

    public void deleteLastUser(){
        db.deleteAddedUserAsTest("test");
        this.list = db.listOfAllUsers();
    }
    public void printAllUsers(){
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(this.list.get(i).toString());
        }
    }

    public User searchUserByPersonalNumber(String personalNumber){
     User fetchedUser = db.searchedUserByPersonalNumber("NR1234");
        System.out.println(fetchedUser);
     return fetchedUser;

    }

    @Override
    public String toString() {
        return "Users{" +
                "db=" + db +
                ", list=" + list +
                ", input=" + input +
                '}';
    }
}
