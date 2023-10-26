package org.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.example.booking.Booking;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class DatabaseTest {

  @Mock Database mockDb;
  private Database db;
  private InputStream originalSystemIn;
  private ByteArrayInputStream simulatedSystemIn;

  /* @Test
  void testCreateNewUserAndGetAllUsers() {

    db.createNewUser(
            "test",
            "test",
            "test@test.com",
            "98741236",
            "TT4124",
            "Lund",
            LocalDate.of(1968, 06, 07));

    ArrayList<User> userList = db.listOfAllUsers();

    boolean newUserFound = false;
    for (User user : users) {
      if (user.getFirstName().equals("test") && user.getLastName().equals("test")) {
        newUserFound = true;
        break;
      }
    }

    assertTrue(newUserFound, "User not found in the list");
  }*/

  @BeforeEach
  void setUp() {
    db = new Database();

    originalSystemIn = System.in;
  }

  @AfterEach
  void tearDown() {}

  @Test
  void testCreateNewUser() {
    String firstName = "soraya";
    String lastName = "Doe";
    String email = "soraya.doe@example.com";
    String contactNumber = "1234567890";
    String personalNumber = "123456789";
    String address = "123 Main St";
    LocalDate dob = LocalDate.of(1990, 05, 15);

    db.createNewUser(firstName, lastName, email, contactNumber, personalNumber, address, dob);
    // hämta user från listan
    List<User> userList = db.listOfAllUsers();

    User createdUser = userList.get(userList.size() - 1);
    assertEquals(firstName, createdUser.getFirstName());
    assertEquals(lastName, createdUser.getLastName());
    assertEquals(email, createdUser.getEmail());
    assertEquals(contactNumber, createdUser.getContactNumber());
    assertEquals(personalNumber, createdUser.getPersonalNumber());
    assertEquals(address, createdUser.getAddress());
    assertEquals(dob, createdUser.getDob());
  }

  @Test
  public void testCreateAUser() {

    String inputString =
        "John\nDoe\njohn.doe@example.com\n1234567890\n123456789\n123 Main St\n1990-05-15";

    simulatedSystemIn = new ByteArrayInputStream(inputString.getBytes());
    System.setIn(simulatedSystemIn);

    Users.createAUser();
    System.setIn(originalSystemIn);

    List<User> userList = db.listOfAllUsers();

    User createdUser = userList.get(userList.size() - 1);
    assertEquals("John", createdUser.getFirstName());
    assertEquals("Doe", createdUser.getLastName());
    assertEquals("john.doe@example.com", createdUser.getEmail());
    assertEquals("1234567890", createdUser.getContactNumber());
    assertEquals("123456789", createdUser.getPersonalNumber());
    assertEquals("123 Main St", createdUser.getAddress());
    assertEquals(LocalDate.of(1990, 5, 15), createdUser.getDob());
  }

  @Test
  void testGetUserByPersonalNumber() {
    String personalNumberToSearch = "NR1234";
    User searchedUser = db.searchedUserByPersonalNumber(personalNumberToSearch);

    Assertions.assertEquals(searchedUser.getPersonalNumber(), personalNumberToSearch);
  }

  @Test
  void testListOfAllRooms() {
    List<Room> rooms = db.listOfAllRooms();

    assertFalse(rooms.isEmpty(), "Room list is empty.");
  }

  @Test
  void testCreateNewEventAndGetAllEvents() {
    db.createNewEvent("Concert", 50.0);
    ArrayList<Event> events = db.listOfAllEvents();

    boolean eventFound = false;
    for (Event event : events) {
      if (event.getEventName().equals("Concert")) {
        eventFound = true;
        break;
      }
    }

    assertTrue(eventFound, "Event not found in the list.");
  }

  @Test
  void testCreateNewBookingFailure() {
    LocalDate date = LocalDate.now();
    boolean paid = true;
    int userId = 1111;
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now();
    int travelersNo = 2;
    double totalPrice = 200.0;
    List<Integer> roomIds = Arrays.asList(1, 2, 3);
    List<Integer> eventIds = Arrays.asList(1, 2, 3);

    int bookingId =
        db.createNewBooking(
            date, paid, userId, startDate, endDate, travelersNo, totalPrice, roomIds, eventIds);

    assertEquals(-1, bookingId);
  }

  @Test
  void testGetBookingSuccess() {
    int bookingId = 127;
    Booking booking = db.getBooking(bookingId);

    assertEquals(bookingId, booking.getBookingId());
  }

  @Test
  void testGetBookingFailure() {
    int bookingId = 111111;
    Booking booking = db.getBooking(bookingId);

    assertNull(booking);
  }

  @Test
  void testGetAllBookingsSuccess() {
    List<Booking> bookingList = db.getAllBookings();
    assertFalse(bookingList.isEmpty());
  }

  @Test
  void testGetAllBookingsFailure() {
    when(mockDb.getAllBookings()).thenReturn(new ArrayList<Booking>());
    List<Booking> bookingList = mockDb.getAllBookings();
    assertTrue(bookingList.isEmpty());
  }

  @Test
  void testFindBookingByUserIdSuccess() {
    int userId = 1;
    Booking booking = db.findBookingByUserId(userId);
    assertNotNull(booking);
  }

  @Test
  void testFindBookingByUserIdFailure() {
    int userId = 11111;
    Booking booking = db.findBookingByUserId(userId);
    assertNull(booking);
  }

  @Test
  void testUpdateBookingSuccess() {
    int bookingId = 116;
    Booking updatedBooking = null;
    String columnName = "paid";
    Booking booking = db.getBooking(bookingId);
    boolean updatedValue = !booking.isPaid();
    if (booking != null) {
      updatedValue = !booking.isPaid();
      updatedBooking = db.updateBooking(bookingId, columnName, updatedValue);
    }

    assertNotNull(updatedBooking);
    assertEquals(updatedValue, updatedBooking.isPaid());
  }

  @Test
  void testUpdateBookingFailure() {
    int bookingId = 1166;
    Booking updatedBooking = null;
    String columnName = "paid";
    Booking booking = db.getBooking(bookingId);
    if (booking != null) {
      boolean updatedValue = !booking.isPaid();
      updatedBooking = db.updateBooking(bookingId, columnName, updatedValue);
    }

    assertNull(updatedBooking);
  }

  @Test
  void testDeleteBookingSuccess() {
    int bookingId = 122;
    boolean deletedBooking = db.deleteBooking(bookingId);
    assertTrue(deletedBooking);
  }

  @Test
  void testDeleteBookingFailure() {
    int bookingId = 1166;
    boolean deletedBooking = db.deleteBooking(bookingId);
    assertFalse(deletedBooking);
  }

  @Test
  void testCreateNewBookingSuccess() {
    LocalDate date = LocalDate.now();
    boolean paid = true;
    int userId = 1;
    LocalDate startDate = LocalDate.of(2023, 11, 1);
    LocalDate endDate = LocalDate.of(2023, 11, 11);
    int travelersNo = 2;
    double totalPrice = 1500.0;
    List<Integer> roomIds = Arrays.asList(1, 2, 3);
    List<Integer> eventIds = Arrays.asList(1, 2, 3);

    int bookingId =
        db.createNewBooking(
            date, paid, userId, startDate, endDate, travelersNo, totalPrice, roomIds, eventIds);

    assertNotEquals(1, bookingId);
  }

  @Test
  void testListOfEventsByStartDate() {
    Date startDate = Date.valueOf("2023-11-11");
    Date endDate = Date.valueOf("2023-11-11");

    List<Event> eventsList = db.listOfEventsByStartDate(startDate, endDate);

    int eventsInEventsList = eventsList.size();

    Assertions.assertEquals(1, eventsInEventsList);
  }
}
