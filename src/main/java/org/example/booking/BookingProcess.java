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

  private final Database db = new Database();
  private final List<Event> selectedEvents = new ArrayList<>();
  private final List<Room> roomListPickedByUser = new ArrayList<>();
  private List<Integer> selectedRoomIds = new ArrayList<>();
  private final LocalDate currentDate = LocalDate.now();
  private List<Integer> selectedEventIds = new ArrayList<>();
   Scanner input = new Scanner(System.in);
  private List<Event> availableEvents;
  private List<Room> allRooms;

  public void createABooking() {

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
            selectedRoomIds,
                selectedEventIds);
    selectedRoomIds = new ArrayList<>();
    selectedEventIds = new ArrayList<>();
    System.out.println(db.getBookingWithDetails(id));
  }

  public User searchUserByPersonalNumber(String personalNumber) {
    User fetchedUser = db.searchedUserByPersonalNumber(personalNumber);
    return fetchedUser;
  }

  private LocalDate getUserInputDate(String prompt) {
    System.out.print(prompt + " (yyyy-MM-dd): ");
    String inputDate = input.nextLine();
    return LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
  }

  public List<Event> searchEventsByStartDate(LocalDate startDate, LocalDate endDate) {

    availableEvents = db.listOfEventsByStartDate(Date.valueOf(startDate), Date.valueOf(endDate));
    displayFetchedEvents(availableEvents);
    return availableEvents;
  }

  private void selectEvents(String eventSelection) {
    String[] eventIds = eventSelection.split(",");
    for (String eventId : eventIds) {
      for (Event event : availableEvents) {
        if (event.getEventId() == Integer.parseInt(eventId)) {
          selectedEvents.add(event);
          selectedEventIds.add(event.getEventId());
        }
      }
    }
  }

  private int getUserInputInt(String prompt) {
    System.out.print(prompt);
    return input.nextInt();
  }

  private void selectRooms() {
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

  public void displayFetchedEvents(List<Event> eventList) {
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
