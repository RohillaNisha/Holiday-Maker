package org.example.booking;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.example.Database;
import org.example.Event;
import org.example.Room;
import org.example.User;

public class BookingProcess {

  private static final Database db = new Database();
  private static final List<Event> selectedEvents = new ArrayList<>();
  private static final List<Room> roomListPickedByUser = new ArrayList<>();
  private static final List<Integer> selectedRoomIds = new ArrayList<>();
  private static final LocalDate currentDate = LocalDate.now();
  static Scanner input = new Scanner(System.in);
  private static List<Event> availableEvents;
  private static List<Room> allRooms;

  public static void createABooking() {

    System.out.println("Enter customer's personal number: ");
    String personalNumber = input.nextLine();
    User fetchedUser = searchUserByPersonalNumber(personalNumber);

    if (fetchedUser == null) {
      System.out.println("This is a new customer. Create the customer first.");
      return;
    }

    LocalDate startDate = getUserInputDate("Enter start date for your trip: ");
    LocalDate endDate = getUserInputDate("Enter end date for your trip: ");
    searchEventsByStartDate(startDate, endDate);

    if (availableEvents.isEmpty()) {
      System.out.println("No events found for the selected date range.");
      return;
    }

    System.out.println("Select id's of the events (comma separated) that you want to add");
    String eventSelection = input.nextLine();
    selectEvents(eventSelection);

    int noOfTravelers = getUserInputInt("Enter number of travelers: ");
    input.nextLine();

    selectRooms();

    int totalCapacity = 0;
    for (Room room : roomListPickedByUser) {
      totalCapacity += room.getRoomCapacity();
    }
    if (noOfTravelers > totalCapacity) {
      System.out.println("You need more rooms");
      System.out.println(allRooms);
      selectRooms();
    }

    int id =
        db.createNewBooking(
            currentDate,
            true,
            fetchedUser.getId(),
            startDate,
            endDate,
            noOfTravelers,
            1500.0,
            selectedRoomIds);
    System.out.println(db.getBookingWithDetails(id));
  }

  public static User searchUserByPersonalNumber(String personalNumber) {
    User fetchedUser = db.searchedUserByPersonalNumber(personalNumber);
    return fetchedUser;
  }

  private static LocalDate getUserInputDate(String prompt) {
    System.out.print(prompt + " (yyyy-MM-dd): ");
    String inputDate = input.nextLine();
    return LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
  }

  public static List<Event> searchEventsByStartDate(LocalDate startDate, LocalDate endDate) {

    availableEvents = db.listOfEventsByStartDate(Date.valueOf(startDate), Date.valueOf(endDate));
    displayFetchedEvents(availableEvents);
    return availableEvents;
  }

  private static void selectEvents(String eventSelection) {
    String[] eventIds = eventSelection.split(",");
    for (String eventId : eventIds) {
      for (Event event : availableEvents) {
        if (event.getEventId() == Integer.parseInt(eventId)) {
          selectedEvents.add(event);
        }
      }
    }
  }

  private static int getUserInputInt(String prompt) {
    System.out.print(prompt);
    return input.nextInt();
  }

  private static void selectRooms() {
    allRooms = db.listOfAllRooms();
    System.out.println(allRooms);
    System.out.println("Please select room IDs (comma separated): ");
    String roomSelection = input.nextLine();
    String[] roomIds = roomSelection.split(",");
    for (String roomId : roomIds) {
      for (Room room : allRooms) {
        if (room.getId() == Integer.parseInt(roomId)) {
          roomListPickedByUser.add(room);
          selectedRoomIds.add(room.getId());
        }
      }
    }
  }

  public static void displayFetchedEvents(List<Event> eventList) {
    System.out.println("No. Event id         Date       Price    Event name");
    for (int i = 0; i < eventList.size(); i++) {

      System.out.println(
          i
              + 1
              + ".) "
              + eventList.get(i).getEventId()
              + "    "
              + eventList.get(i).getStartDate()
              + "      "
              + eventList.get(i).getEventPrice()
              + " "
              + eventList.get(i).getEventName());
    }
  }
}
