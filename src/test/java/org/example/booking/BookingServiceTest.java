package org.example.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.example.Database;
import org.example.Event;
import org.example.Room;
import org.example.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingServiceTest {

  LocalDate startDate = LocalDate.of(2023, 10, 1);
  LocalDate endDate = LocalDate.of(2023, 11, 1);
  private BookingService bookingService;
  private Database mockDatabase;

  @BeforeEach
  void setUp() {
    mockDatabase = mock(Database.class);
    bookingService = new BookingService(mockDatabase);
  }

  @Test
  void testSearchUserByPersonalNumber() {

    String personalNumber = "NR1234";
    User expectedUser =
        new User(
            1,
            "Test",
            "User",
            "test@test.com",
            "123456",
            "NR1234",
            "Sweden",
            LocalDate.of(1900, 1, 1));

    when(mockDatabase.searchedUserByPersonalNumber(personalNumber)).thenReturn(expectedUser);
    User actualUser = mockDatabase.searchedUserByPersonalNumber(personalNumber);

    assertEquals(expectedUser, actualUser);
    assertEquals("Sweden", actualUser.getAddress());
  }

  @Test
  void testSearchEventsByStartDate() {

    List<Event> mockEvents = new ArrayList<>();
    mockEvents.add(new Event("Event 1", 500, 101, startDate, endDate));
    mockEvents.add(new Event("Event 2", 1000, 102, startDate, endDate));

    when(mockDatabase.listOfEventsByStartDate(Date.valueOf(startDate), Date.valueOf(endDate)))
        .thenReturn(mockEvents);

    List<Event> availableEvents = bookingService.searchEventsByDate(startDate, endDate);

    assertEquals(2, availableEvents.size());
    assertEquals("Event 1", availableEvents.get(0).getEventName());
    assertEquals("Event 2", availableEvents.get(1).getEventName());

    verify(mockDatabase).listOfEventsByStartDate(Date.valueOf(startDate), Date.valueOf(endDate));
  }

  @Test
  void testSelectedEvents() {
    List<Event> availableEvents = new ArrayList<>();
    availableEvents.add(new Event("Event 1", 500, 101, startDate, endDate));
    availableEvents.add(new Event("Event 2", 1000, 102, startDate, endDate));
    availableEvents.add(new Event("Event 3", 1500, 103, startDate, endDate));
    availableEvents.get(0).setEventId(1);
    availableEvents.get(1).setEventId(2);
    availableEvents.get(2).setEventId(3);

    String eventSelection = "1,3";
    List<Event> selectedEvents = bookingService.selectEvents(availableEvents, eventSelection);

    assertNotNull(selectedEvents);
    assertEquals(2, selectedEvents.size());

    assertTrue(selectedEvents.stream().anyMatch(event -> event.getEventId() == 1));
    assertTrue(selectedEvents.stream().anyMatch(event -> event.getEventId() == 3));

    assertFalse(selectedEvents.stream().anyMatch(event -> event.getEventId() == 2));
  }

  @Test
  void testGetAvailableRooms() {
    List<Room> mockRooms = new ArrayList<>();
    mockRooms.add(new Room(1, 201, "Single Room", 800, 1));
    mockRooms.add(new Room(2, 202, "Double Room", 1400, 2));
    mockRooms.add(new Room(3, 203, "Family Room", 2400, 2));

    when(mockDatabase.listOfAllRooms()).thenReturn(mockRooms);
    bookingService.getAvailableRooms();

    assertEquals(3, mockRooms.size());
    assertEquals("Single Room", mockRooms.get(0).getRoomType());
    assertEquals(2, mockRooms.get(1).getId());
  }

  @Test
  void testSelectedRooms() {
    List<Room> availableRooms = new ArrayList<>();
    availableRooms.add(new Room(1, 201, "Single Room", 800, 1));
    availableRooms.add(new Room(2, 202, "Double Room", 1400, 2));
    availableRooms.add(new Room(3, 203, "Family Room", 2400, 2));

    String roomSelection = "1,2";

    List<Room> selectedRooms = bookingService.selectRooms(availableRooms, roomSelection);

    assertNotNull(selectedRooms);
    assertEquals(2, selectedRooms.size());

    assertTrue(selectedRooms.stream().anyMatch(room -> room.getId() == 1));
    assertTrue(selectedRooms.stream().anyMatch(room -> room.getId() == 2));

    assertFalse(selectedRooms.stream().anyMatch(room -> room.getId() == 3));
  }

  @Test
  void testCalculateTotalPrice() {
    List<Event> selectedEvents = new ArrayList<>();
    selectedEvents.add(new Event("Event 1", 500, 101, startDate, endDate));
    selectedEvents.add(new Event("Event 2", 1000, 102, startDate, endDate));
    selectedEvents.get(0).setEventId(1);
    selectedEvents.get(1).setEventId(2);

    List<Room> selectedRooms = new ArrayList<>();
    selectedRooms.add(new Room(1, 201, "Single Room", 800, 1));
    selectedRooms.add(new Room(2, 202, "Double Room", 1400, 2));

    int noOfTravelers = 3;


    double totalPrice = bookingService.calculateTotalPrice(selectedEvents, selectedRooms, noOfTravelers);

    double expectedTotalPrice = 6700;

    assertEquals(expectedTotalPrice, totalPrice);
  }
}
