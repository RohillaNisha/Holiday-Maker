package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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



}