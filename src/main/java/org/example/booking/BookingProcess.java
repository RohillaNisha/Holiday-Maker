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
  private List<Event> selectedEvents = new ArrayList<>();
  private List<Room> roomListPickedByUser = new ArrayList<>();
  private List<Integer> selectedRoomIds = new ArrayList<>();
  private final LocalDate currentDate = LocalDate.now();
  private List<Integer> selectedEventIds = new ArrayList<>();
  Scanner input = new Scanner(System.in);
  private List<Event> availableEvents;
  private double totalPrice = 0;
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

    calculateTotalPrice(noOfTravelers);

    int id =
        db.createNewBooking(
            currentDate,
            false,
            fetchedUser.getId(),
            startDate,
            endDate,
            noOfTravelers,
            totalPrice,
            selectedRoomIds,
            selectedEventIds);
    reset();

    System.out.println("Booking is done with booking id: " + id);
  }

  private User searchUserByPersonalNumber(String personalNumber) {
    User fetchedUser = db.searchedUserByPersonalNumber(personalNumber);
    return fetchedUser;
  }

  private LocalDate getUserInputDate(String prompt) {
    System.out.print(prompt + " (yyyy-MM-dd): ");
    String inputDate = input.nextLine();
    return LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
  }

  private List<Event> searchEventsByStartDate(LocalDate startDate, LocalDate endDate) {

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

  private void displayFetchedEvents(List<Event> eventList) {
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

  private void calculateTotalPrice(int noOfTravelers){
    for(Event event : selectedEvents){
      totalPrice += event.getEventPrice() * noOfTravelers;
    }
    for(Room room : roomListPickedByUser){
      totalPrice += room.getRoomPrice();
    }
  }

  public void findABooking(){
    int bookingIdForSearch = getUserInputInt("Enter your booking id: ");
    input.nextLine();
    System.out.println(db.getBookingWithDetails(bookingIdForSearch));

  }

  public void changePaidStatus(){
    int bookingIdForSearch = getUserInputInt("Enter your booking id: ");
    Booking booking = db.getBookingWithDetails(bookingIdForSearch);
    System.out.println(booking);
    int userInput = getUserInputInt("Click 1 to make it paid. Click 2 to make it unpaid");
    input.nextLine();
    if(userInput == 1){
      db.updateBooking(booking.getBookingId(), "paid", true);
      System.out.println("Booking is now paid");
    }else {
      db.updateBooking(booking.getBookingId(), "paid", false);
      System.out.println("Booking is now unpaid");
    }
  }

  private void reset(){
    selectedRoomIds = new ArrayList<>();
    selectedEventIds = new ArrayList<>();
    selectedEvents = new ArrayList<>();
    roomListPickedByUser = new ArrayList<>();
    totalPrice = 0;
  }
}
