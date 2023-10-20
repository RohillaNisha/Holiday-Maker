package org.example;


import java.sql.*;
import java.time.LocalDate;
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

    void createNewUser(String firstName, String lastName, String email, String contactNumber, String personalNumber, String address, LocalDate dob){
        try {
            statement = conn.prepareStatement("INSERT INTO users SET firstName = ?, lastName = ?, email = ?, contactNumber = ?, personalNumber = ?, address = ?, dob = ?");
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setString(3,email);
            statement.setString(4,contactNumber);
            statement.setString(5,personalNumber);
            statement.setString(6, address);
            statement.setDate(7, Date.valueOf(dob));
            statement.executeUpdate();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    void deleteAddedUserAsTest(String firstName){
        try{
            statement = conn.prepareStatement("DELETE FROM users WHERE firstName = ? ");
            statement.setString(1,firstName);
            statement.executeUpdate();
        }  catch (Exception ex) { ex.printStackTrace(); }
    }

    public ArrayList<User> listOfAllUsers(){
        getAllUsers();
        ArrayList<User> tempList = new ArrayList<User>();
        try {
            while (resultSet.next()) {

                java.sql.Date date = resultSet.getDate("dob");
                LocalDate localDate = date.toLocalDate();
                tempList.add(new User(resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getString("contactNumber"),
                        resultSet.getString("personalNumber"),
                        resultSet.getString("address"),
                        localDate));
            }
        } catch (Exception ex){ ex.printStackTrace(); }
        return tempList;
    }


    public void getUserByPersonalNumber(String personalNumber){
        try {
            statement = conn.prepareStatement("SELECT * FROM users WHERE personalNumber = ?");
            statement.setString(1,personalNumber);
            resultSet = statement.executeQuery();
            System.out.println("fetchedUser by personal no is " + resultSet);
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public User searchedUserByPersonalNumber(String personalNumber){
       getUserByPersonalNumber(personalNumber);
     User fetchedUser;
     try {
         if (resultSet.next()) {
             java.sql.Date date = resultSet.getDate("dob");
             LocalDate localDate = date.toLocalDate();
             fetchedUser = new User(
                     resultSet.getInt("id"),
                     resultSet.getString("firstName"),
                     resultSet.getString("lastName"),
                     resultSet.getString("email"),
                     resultSet.getString("contactNumber"),
                     resultSet.getString("personalNumber"),
                     resultSet.getString("address"),
                     localDate);
             return fetchedUser;
         }
     }
     catch(Exception ex) {
         ex.printStackTrace();
     };
        return null;
    }


    public ArrayList<Room> listOfAllRooms() {
        getAllRooms();
        ArrayList<Room> tempList = new ArrayList<Room>();
        try {
            while (resultSet.next()) {
                tempList.add(new Room(resultSet.getInt("roomId"),
                        resultSet.getInt("roomNumber"),
                        resultSet.getString("RoomType"),
                        resultSet.getDouble("roomPrice")));
            }
        } catch (Exception ex){ ex.printStackTrace(); }
        return tempList;
    }

    private void getAllRooms() {
        try {
            statement = conn.prepareStatement("SELECT * FROM rooms");
            resultSet = statement.executeQuery();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    void createNewEvent(String eventName, double eventPrice){
        try {
            statement = conn.prepareStatement("INSERT INTO events SET eventName = ?, eventPrice = ?");
            statement.setString(1,eventName);
            statement.setDouble(2,eventPrice);
            statement.executeUpdate();
        } catch (Exception ex) { ex.printStackTrace();
        }
    }

    void getAllEvents(){
        try {
            statement = conn.prepareStatement("SELECT * FROM events");
            resultSet = statement.executeQuery();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public ArrayList<Event> listOfAllEvents(){
        getAllEvents();
        ArrayList<Event> tempList = new ArrayList<Event>();
        try {
            while (resultSet.next()) {
                tempList.add(new Event(resultSet.getInt("eventId"),
                        resultSet.getString("eventName"),
                        resultSet.getDouble("eventPrice")));
            }
        } catch (Exception ex){ ex.printStackTrace(); }
        return tempList;
    }


}