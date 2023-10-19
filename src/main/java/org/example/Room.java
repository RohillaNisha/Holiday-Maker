package org.example;

public class Room {

    private  int id;
    private int roomNumber;
    private String roomType;
    private double roomPrice;

    public Room(int id, int roomNumber, String roomType, double roomPrice) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }

    public int getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", roomType='" + roomType + '\'' +
                ", roomPrice=" + roomPrice +
                '}';
    }
}
