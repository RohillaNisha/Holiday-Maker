package org.example.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking {
  private final LocalDate bookingDate;
  private final boolean paid;
  private final int userId;
  private final LocalDate tripStartDate;
  private final LocalDate tripEndDate;
  private final int noOfTravellers;
  private final double totalPrice;
  private int packageId;
  private int eventId;
  private int bookingId;
  private List<Integer> roomIds = new ArrayList<>();

  public Booking(
      LocalDate bookingDate,
      boolean paid,
      int userId,
      LocalDate tripStartDate,
      LocalDate tripEndDate,
      int noOfTravellers,
      double totalPrice) {

    this.bookingDate = bookingDate;
    this.paid = paid;
    this.userId = userId;
    this.tripStartDate = tripStartDate;
    this.tripEndDate = tripEndDate;
    this.noOfTravellers = noOfTravellers;
    this.totalPrice = totalPrice;
  }

  public void setRoomIds(List<Integer> roomIds) {

    this.roomIds = roomIds;
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

  public int getNoOfTravellers() {
    return noOfTravellers;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public int getPackageId() {
    return packageId;
  }

  public void setPackageId(int packageId) {

    this.packageId = packageId;
  }

  public int getEventId() {
    return eventId;
  }

  public void setEventId(int eventId) {

    this.eventId = eventId;
  }

  @Override
  public String toString() {

    return "Booking{"
        + "bookingDate="
        + bookingDate
        + ", paid="
        + paid
        + ", userId="
        + userId
        + ", tripStartDate="
        + tripStartDate
        + ", tripEndDate="
        + tripEndDate
        + ", noOfTravellers="
        + noOfTravellers
        + ", totalPrice="
        + totalPrice
        + ", packageId="
        + packageId
        + ", eventId="
        + eventId
        + ", bookingId="
        + bookingId
        + ", roomIds="
        + roomIds
        + '}';
  }
}
