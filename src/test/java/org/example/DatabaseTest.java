package org.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
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

  @BeforeEach
  void setUp() {
    db = new Database();
  }

  @AfterEach
  void tearDown() {}

  @Test
  void testCreateNewUserAndGetAllUsers() {

    db.createNewUser(
        "test", "test", "test@test.com", "98741236", "TT4124", "Lund", LocalDate.of(1968, 06, 07));
    ArrayList<User> users = db.listOfAllUsers();

    boolean newUserFound = false;
    for (User user : users) {
      if (user.getFirstName().equals("test") && user.getLastName().equals("test")) {
        newUserFound = true;
        break;
      }
    }

    assertTrue(newUserFound, "User not found in the list");
  }

  @Test
  void testGetUserByPersonalNumber() {
    String personalNumberToSearch = "NR1234";
    User searchedUser = db.searchedUserByPersonalNumber(personalNumberToSearch);

    Assertions.assertEquals(searchedUser.getPersonalNumber(), personalNumberToSearch);
  }

  @Test
  void testListOfAllRooms() {
    ArrayList<Room> rooms = db.listOfAllRooms();

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
  void testCreateNewBookingSuccess() {
    LocalDate date = LocalDate.now();
    boolean paid = true;
    int userId = 1;
    LocalDate startDate = LocalDate.of(2023, 11, 1);
    LocalDate endDate = LocalDate.of(2023, 11, 11);
    int roomId = 1;
    int travelersNo = 2;
    double totalPrice = 1500.0;
    int packageId = 201;
    int eventId = 1;

    int bookingId =
        db.createNewBooking(
            date,
            paid,
            userId,
            startDate,
            endDate,
            roomId,
            travelersNo,
            totalPrice,
            packageId,
            eventId);

    assertNotEquals(0, bookingId);
  }

  @Test
  void testCreateNewBookingFailure() {
    LocalDate date = LocalDate.now();
    boolean paid = true;
    int userId = 111111;
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = LocalDate.now();
    int roomId = 1;
    int travelersNo = 2;
    double totalPrice = 200.0;
    int packageId = 201;
    int eventId = 1;

    int bookingId =
        db.createNewBooking(
            date,
            paid,
            userId,
            startDate,
            endDate,
            roomId,
            travelersNo,
            totalPrice,
            packageId,
            eventId);

    assertEquals(-1, bookingId);
  }

  @Test
  void testGetBookingSuccess() {
    int bookingId = 116;
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
  void testDeleteBookingSuccess(){
    int bookingId = 122;
    boolean deletedBooking = db.deleteBooking(bookingId);
    assertTrue( deletedBooking);
  }

  @Test
  void testDeleteBookingFailure() {
    int bookingId = 1166;
    boolean deletedBooking = db.deleteBooking(bookingId);
    assertFalse( deletedBooking);
  }
}
