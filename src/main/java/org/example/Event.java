package org.example;

public class Event {
    private int eventId;
    private String eventName;
    private double eventPrice;
    private int packageId;


    //constructor


    public Event(int eventId, String eventName, double eventPrice) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventPrice = eventPrice;
        this.packageId = packageId;
    }

    //getter

    public int getEventId() {

        return eventId;
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
