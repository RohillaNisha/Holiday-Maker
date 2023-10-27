package org.example;

public class Room {

    private  int id;
    private int roomNumber;
    private String roomType;
    private double roomPrice;
    private int roomCapacity;

    public Room(int id, int roomNumber, String roomType, double roomPrice, int roomCapacity) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomCapacity = roomCapacity;
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

    public int getRoomCapacity() {
        return roomCapacity;
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
