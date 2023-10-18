package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Database {
    ResultSet resultSet;
    PreparedStatement statement;
    Connection conn = null;

    public Database(){
        connectToDb();

    }

    void connectToDb(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://161.97.144.27:8012/holidayMaker?user=root&password=practicespiderclimb&serverTimezone=UTC");
            
        } catch (Exception ex) { ex.printStackTrace();
        }
    }

    void getAllUsers(){
        try {
            statement = conn.prepareStatement("SELECT * FROM users");
            resultSet = statement.executeQuery();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    void createNewUser(String firstName, String lastName, String email){
        try {
            statement = conn.prepareStatement("INSERT INTO users SET firstName = ?, lastName = ?, email = ?");
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setString(3,email);
            statement.executeUpdate();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    void deleteLastAddedUser(){
        try{
            statement = conn.prepareStatement("DELETE FROM users ORDER BY id DESC LIMIT 1 ");
            statement.executeUpdate();
        }  catch (Exception ex) { ex.printStackTrace(); }
    }

    public ArrayList<User> listOfAllUsers(){
        getAllUsers();
        ArrayList<User> tempList = new ArrayList<User>();
        try {
            while (resultSet.next()) {
                tempList.add(new User(resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email")));
            }
        } catch (Exception ex){ ex.printStackTrace(); }
        return tempList;
    }



}