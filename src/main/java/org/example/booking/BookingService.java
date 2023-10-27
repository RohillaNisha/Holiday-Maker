package org.example.booking;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.example.Database;
import org.example.Event;
import org.example.Room;
import org.example.User;

public class BookingService {
  private final Database database;

  public BookingService(Database database) {
    this.database = database;
  }

  public User searchUserByPersonalNumber(String personalNumber) {

    return database.searchedUserByPersonalNumber(personalNumber);
  }

  public List<Event> searchEventsByDate(LocalDate startDate, LocalDate endDate) {
    return database.listOfEventsByStartDate(Date.valueOf(startDate), Date.valueOf(endDate));
  }
  public List<Package> searchPackagesByDate(LocalDate startDate, LocalDate endDate) {
    return database.listOfEventsByStartDate(Date.valueOf(startDate), Date.valueOf(endDate));
  }


  public List<Event> selectEvents(List<Event> availableEvents, String eventSelection) {
    String[] eventIds = eventSelection.split(",");
    List<Event> selectedEvents = new ArrayList<>();

    for (String eventId : eventIds) {
      for (Event event : availableEvents) {
        if (event.getEventId() == Integer.parseInt(eventId)) {
          selectedEvents.add(event);
          break;
        }
      }
    }

    return selectedEvents;
  }

  public List<Room> getAvailableRooms() {
    return database.listOfAllRooms();
  }


  public List<Room> selectRooms(List<Room> availableRooms, String roomSelection) {

    String[] roomIds = roomSelection.split(",");
    List<Room> selectedRooms = new ArrayList<>();

    for (String roomId : roomIds) {
      for (Room room : availableRooms) {
        if (room.getId() == Integer.parseInt(roomId)) {
          selectedRooms.add(room);
        }
      }
    }
    return selectedRooms;
  }


  public double calculateTotalPrice(
      List<Event> selectedEvents, List<Room> selectedRooms, int noOfTravelers) {
    double totalPrice = 0.0;

    for (Event event : selectedEvents) {
      totalPrice += event.getEventPrice() * noOfTravelers;
    }

    for (Room room : selectedRooms) {
      totalPrice += room.getRoomPrice();
    }

    return totalPrice;
  }

  public int createBooking(
      LocalDate date,
      boolean paid,
      int userId,
      LocalDate startDate,
      LocalDate endDate,
      int travelersNo,
      double totalPrice,
      List<Integer> roomIds,
      List<Integer> eventIds) {
    return database.createNewBooking(
        date, paid, userId, startDate, endDate, travelersNo, totalPrice, roomIds, eventIds);
  }
}
