package org.example.booking;

import org.example.Database;
import org.example.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Bookings {

    private static Database db;

    private ArrayList<Booking> bookingsList;

    public Bookings() {
        this.db = new Database();
        this.bookingsList = db.listOfAllBookings();
    }

    public int getTotalNumberOfBookings() {
        return this.bookingsList.size();
    }



}
