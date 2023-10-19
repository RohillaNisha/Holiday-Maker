package org.example;

import java.util.ArrayList;

public class Rooms {

    private Database db;
    private ArrayList<Room> roomList;

    public Rooms() {
        this.db = new Database();
        this.roomList = db.listOfAllRooms();
    }

    public String getRoomType() {
        return this.roomList.get(0).getRoomType();
    }

    public double getMostExpensiveRoom() {
        double highestPrice = 0;

        for (Room room : roomList) {
            double roomPrice = room.getRoomPrice();
            if (roomPrice > highestPrice) {
                highestPrice = roomPrice;
            }
        }
        return highestPrice;
    }

    public int getNumberOfRooms() {
        int numberOfRooms = 0;

        for(Room room : roomList){
            numberOfRooms++;
        }
        return numberOfRooms;
    }

    public int getNumberOfCheapRooms() {
        int numberOfRooms = 0;

        for (Room room : roomList){
            if(room.getRoomPrice() < 1000){
                numberOfRooms++;
            }
        }
        return numberOfRooms;
    }
}
