package org.example.booking;

import org.example.Database;
import org.example.Room;
import org.example.User;

import java.time.LocalDate;
import java.util.Scanner;

public class Booking {
    private final int bookingId;
    private final LocalDate bookingDate;
    private final boolean paid;

    private  User user;

    private final LocalDate tripStartDate;
    private final LocalDate tripEndDate;
    private  int roomId;
    private int noOfTravellers;

    private Database db = new Database();


    public Booking(int bookingId, LocalDate bookingDate, boolean paid, LocalDate tripStartDate, LocalDate tripEndDate, int roomId, int noOfTravellers ) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.paid = paid;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
        this.roomId = roomId;
        this.noOfTravellers= noOfTravellers;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public int getBookingId() {
        return bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }


    public LocalDate getTripStartDate() {
        return tripStartDate;
    }

    public LocalDate getTripEndDate() {
        return tripEndDate;
    }

    public int getRoom() {
        return roomId;
    }

    public int getNoOfTravellers() {
        return noOfTravellers;
    }



    public boolean isPaid() {
        return paid;
    }


    
}
