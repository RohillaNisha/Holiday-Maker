package org.example;

import java.time.LocalDate;
import java.util.List;

public class Event {
    private int eventId;
    private String eventName;
    private double eventPrice;
    private int packageId;

    private LocalDate startDate;
    private LocalDate endDate;


    //constructor


    public Event( String eventName, double eventPrice, int packageId, LocalDate startDate, LocalDate endDate) {
        this.eventName = eventName;
        this.eventPrice = eventPrice;
        this.packageId = packageId;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


//getter

    public int getEventId() {

        return eventId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getEventName() {

        return eventName;
    }

    public double getEventPrice() {

        return eventPrice;
    }

    public int getPackageId() {
        return packageId;
    }


    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventPrice=" + eventPrice +
                ", packageId=" + packageId +
                '}';
    }
}
