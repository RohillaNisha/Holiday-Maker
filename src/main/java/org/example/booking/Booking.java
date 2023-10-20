package org.example.booking;

import java.time.LocalDate;

public class Booking {
    private final int id;
    private final LocalDate date;
    private final boolean paid;

    public Booking(int id, LocalDate date, boolean status) {
        this.id = id;
        this.date = date;
        this.paid = status;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPaid() {
        return paid;
    }
    
}
