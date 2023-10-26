package org.example.booking;

import java.time.LocalDate;
import java.util.List;
import org.example.Event;
import org.example.Room;
import org.example.User;

public class EventBookingProcess {
  private final BookingService bookingService;
  private final UserInteraction userInteraction;

  private final LocalDate currentDate = LocalDate.now();

  public EventBookingProcess(BookingService bookingService, UserInteraction userInteraction) {

    this.bookingService = bookingService;
    this.userInteraction = userInteraction;
  }

  public void createABooking() {
    String personalNumber = userInteraction.getUserInput("Enter customer's personal number: ");
    User fetchedUser = bookingService.searchUserByPersonalNumber(personalNumber);

    if (fetchedUser == null) {
      userInteraction.displayMessage("This is a new customer. Create the customer first.");
      return;
    }

    LocalDate startDate = userInteraction.getUserInputDate("Enter start date for your trip: ");
    LocalDate endDate = userInteraction.getUserInputDate("Enter end date for your trip: ");

    List<Event> availableEvents = bookingService.searchEventsByDate(startDate, endDate);

    if (availableEvents.isEmpty()) {
      userInteraction.displayMessage("No events found for the selected date range.");
    }

    displayFetchedEvents(availableEvents);

    String eventSelection =
        userInteraction.getUserInput(
            "Select id's of the events (comma separated) that you want to add: ");
    List<Event> selectedEvents = bookingService.selectEvents(availableEvents, eventSelection);
    List<Integer> selectedEventIds = selectedEvents.stream().map(Event::getEventId).toList();

    int noOfTravelers = userInteraction.getUserInputInt("Enter number of travelers: ");

    List<Room> availableRooms = bookingService.getAvailableRooms();
    displayFetchedRooms(availableRooms);

    String roomSelection =
        userInteraction.getUserInput(
            "Select id's of the rooms (comma separated) that you want to add: ");
    List<Room> selectedRooms = bookingService.selectRooms(availableRooms, roomSelection);

    int totalCapacity = 0;
    for (Room room : selectedRooms) {
      totalCapacity += room.getRoomCapacity();
    }

    while (noOfTravelers > totalCapacity) {
      userInteraction.displayMessage("You need more rooms");
      String addedRoomSelection =
          userInteraction.getUserInput(
              "Select id's of the rooms (comma separated) that you want to add:");

      List<Room> addedRoom = bookingService.selectRooms(availableRooms, addedRoomSelection);

      boolean canAccommodate = true;
      for (Room room : addedRoom) {
        if (room.getRoomCapacity() == 0) {
          canAccommodate = false;
          break;
        }
      }

      if (canAccommodate) {
        selectedRooms.addAll(addedRoom);
        totalCapacity = 0;
        for (Room room : selectedRooms) {
          totalCapacity += room.getRoomCapacity();
        }
      }
    }

    List<Integer> selectedRoomIds = selectedRooms.stream().map(Room::getId).toList();

    double totalPrice =
        bookingService.calculateTotalPrice(selectedEvents, selectedRooms, noOfTravelers);

    int id =
        bookingService.createBooking(
            currentDate,
            false,
            fetchedUser.getId(),
            startDate,
            endDate,
            noOfTravelers,
            totalPrice,
            selectedRoomIds,
            selectedEventIds);

    userInteraction.displayMessage("Booking is done with booking id: " + id);
  }

  private void displayFetchedEvents(List<Event> eventList) {

    System.out.printf(
        "-------------------------------------------------------------------------------------------------------%n");
    System.out.printf(
        "                                          AVAILABLE EVENTS                                             %n");
    System.out.printf(
        "-------------------------------------------------------------------------------------------------------%n");

    System.out.printf(
        "| %-4s | %-8s | %-40s | %-12S | %-12S | %-8S |%n",
        "NO.", "EVENT ID", "EVENT NAME", "START DATE", "END DATE", "PRICE");

    System.out.printf(
        "-------------------------------------------------------------------------------------------------------%n");
    for (int i = 0; i < eventList.size(); i++) {
      System.out.printf(
          "| %-4s | %-8s | %-40s | %-12S | %-12S | %-8S |%n",
          i + 1 + ".)",
          eventList.get(i).getEventId(),
          eventList.get(i).getEventName(),
          eventList.get(i).getStartDate(),
          eventList.get(i).getEndDate(),
          eventList.get(i).getEventPrice());
    }
    System.out.printf(
        "-------------------------------------------------------------------------------------------------------%n\n");
  }

  private void displayFetchedRooms(List<Room> roomList) {
    System.out.printf("--------------------------------------------------------------%n");
    System.out.printf("                       AVAILABLE ROOMS                        %n");
    System.out.printf("--------------------------------------------------------------%n");

    System.out.printf(
        "| %-4s | %-4s | %-9s | %-8S | %-10S | %-8S |%n",
        "NO.", "ID", "ROOM TYPE", "R.NUMBER", "R.CAPACITY", "PRICE");
    System.out.printf("--------------------------------------------------------------%n");

    for (int i = 0; i < roomList.size(); i++) {
      System.out.printf(
          "| %-4s | %-4s | %-9s | %-8S | %-10S | %-8S |%n",
          i + 1 + ".)",
          roomList.get(i).getId(),
          roomList.get(i).getRoomType(),
          roomList.get(i).getRoomNumber(),
          roomList.get(i).getRoomCapacity(),
          roomList.get(i).getRoomPrice());
    }

    System.out.printf("--------------------------------------------------------------%n\n");
  }
}
