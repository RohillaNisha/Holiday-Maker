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
         ;}
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
}