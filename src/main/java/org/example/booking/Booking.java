package org.example.booking;

import java.time.LocalDate;

public class Booking {
    private final LocalDate bookingDate;
    private final boolean paid;
    private final int userId;
    private final LocalDate tripStartDate;
    private final LocalDate tripEndDate;
    private final int roomId;
    private final int noOfTravellers;
    private final double totalPrice;
    private final int packageId;
    private final int eventId;
    private int bookingId;

    public Booking(
            LocalDate bookingDate,
            boolean paid,
            int userId,
            LocalDate tripStartDate,
            LocalDate tripEndDate,
            int roomId,
            int noOfTravellers,
            double totalPrice,
            int packageId,
            int eventId) {
        this.bookingDate = bookingDate;
        this.paid = paid;
        this.userId = userId;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
        this.roomId = roomId;
        this.noOfTravellers = noOfTravellers;
        this.totalPrice = totalPrice;
        this.packageId = packageId;
        this.eventId = eventId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDate getTripStartDate() {
        return tripStartDate;
    }

    public LocalDate getTripEndDate() {
        return tripEndDate;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getNoOfTravellers() {
        return noOfTravellers;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getPackageId() {
        return packageId;
    }

    public int getEventId() {
        return eventId;
    }
}