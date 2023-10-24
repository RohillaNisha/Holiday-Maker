package org.example.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking {
  private final LocalDate bookingDate;
  private boolean paid;
  private final int userId;
  private String userName;
  private final LocalDate tripStartDate;
  private final LocalDate tripEndDate;
  private final int noOfTravellers;
  private final double totalPrice;
  private int packageId;
  private List<Integer> eventIds = new ArrayList<>();
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

  public void setUserName(String userName) {
    this.userName = userName;
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

  public void setEventIds(List<Integer> eventIds) {
    this.eventIds = eventIds;
  }

  @Override
  public String toString() {

    return "Booking{"
        + "bookingDate="
        + bookingDate
        + ", paid="
        + paid
        + ", userName="
        + userName
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
        + ", eventIds="
        + eventIds
        + ", bookingId="
        + bookingId
        + ", roomIds="
        + roomIds
        + '}';
  }
}
