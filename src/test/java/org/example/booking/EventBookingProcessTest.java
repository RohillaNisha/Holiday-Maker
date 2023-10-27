package org.example.booking;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.example.Event;
import org.example.Room;
import org.example.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventBookingProcessTest {

  private final LocalDate startDate = LocalDate.of(2023, 10, 1);
  private final LocalDate endDate = LocalDate.of(2023, 11, 1);
  private EventBookingProcess eventBookingProcess;
  private BookingService bookingService;
  private UserInteraction userInteraction;

  @BeforeEach
  void setUp() {
    bookingService = mock(BookingService.class);
    userInteraction = mock(UserInteraction.class);
    User mockedUser =
        new User(
            1,
            "Test",
            "User",
            "test@test.com",
            "123456",
            "NR1234",
            "Sweden",
            LocalDate.of(1900, 1, 1));

    eventBookingProcess = new EventBookingProcess(bookingService, userInteraction);

    when(bookingService.searchUserByPersonalNumber(anyString())).thenReturn(mockedUser);
  }

  @Test
  void testCreateABooking() {

    List<Event> selectedEvents = new ArrayList<>();
    selectedEvents.add(new Event("Event 1", 500, 101, startDate, endDate));
    selectedEvents.add(new Event("Event 2", 1000, 102, startDate, endDate));
    selectedEvents.get(0).setEventId(1);
    selectedEvents.get(1).setEventId(2);

    List<Room> selectedRooms = new ArrayList<>();
    selectedRooms.add(new Room(1, 201, "Single Room", 800, 1));
    selectedRooms.add(new Room(2, 202, "Double Room", 1400, 2));

    when(userInteraction.getUserInput("Enter customer's personal number: ")).thenReturn("NR1234");
    when(userInteraction.getUserInput("Enter start date for your trip: "))
        .thenReturn(String.valueOf(startDate));
    when(userInteraction.getUserInput("Enter end date for your trip: "))
        .thenReturn(String.valueOf(endDate));
    when(userInteraction.getUserInput(
            "Select id's of the events (comma separated) that you want to add"))
        .thenReturn("1,2");
    when(userInteraction.getUserInputInt("Enter number of travelers: ")).thenReturn(3);

    //    when(bookingService.selectEvents(anyList(), eq(anyString()))).thenReturn(selectedEvents);
    //    when(bookingService.selectRooms(anyList(), eq(anyString()))).thenReturn(selectedRooms);

    eventBookingProcess.createABooking();

    verify(bookingService).searchUserByPersonalNumber("NR1234");
    //    verify(bookingService).selectEvents(anyList(), eq("1,2"));
    //    verify(bookingService).selectRooms(anyList(), eq("1,2"));
  }
}
