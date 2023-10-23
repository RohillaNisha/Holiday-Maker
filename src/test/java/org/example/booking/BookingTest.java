package org.example.booking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
<<<<<<< HEAD

import org.example.Database;
import org.example.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookingTest {
  private Database db = new Database();

  Booking booking = new Booking(102, LocalDate.parse("2023-10-20"), true, 1,  LocalDate.parse("2023-10-17"), LocalDate.parse("2023-10-19"), 2, 3);

  @Test
  void testGetBookingId() {
    assertEquals(102, booking.getBookingId());
=======
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingTest {
  private Booking booking;

  @BeforeEach
  void setUp() {

    booking =
        new Booking(
            LocalDate.now(),
            true,
            1,
            LocalDate.of(2023, 11, 1),
            LocalDate.of(2023, 11, 11),
            1,
            2,
            1500.0,
            201,
            1);
  }

  @Test
  void testGetBookingId() {
    booking.setBookingId(101);
    assertEquals(101, booking.getBookingId());
>>>>>>> devBranch
  }

  @Test
  void testGetBookingDate() {
<<<<<<< HEAD
    assertEquals(LocalDate.parse("2023-10-20"), booking.getBookingDate());
=======
    assertEquals(LocalDate.now(), booking.getBookingDate());
>>>>>>> devBranch
  }

  @Test
  void testGetPaidStatus() {
    assertTrue(booking.isPaid());
  }

<<<<<<< HEAD

  @Test

  public void testSearchUserByPersonalNumber() {
    String personalNumber = "NR1234";
    User fetchedUser = db.searchedUserByPersonalNumber(personalNumber);
    Assertions.assertEquals(fetchedUser.getPersonalNumber(), "NR1234");

  }

=======
  @Test
  void testGetUseId() {
    assertEquals(1, booking.getUserId());
  }

  @Test
  void testGetTripStartDate() {
    assertEquals(LocalDate.parse("2023-11-01"), booking.getTripStartDate());
  }

  @Test
  void testGetTripEndDate() {
    assertEquals(LocalDate.parse("2023-11-11"), booking.getTripEndDate());
  }

  @Test
  void testGetRoomID() {
    assertEquals(1, booking.getRoomId());
  }

  @Test
  void testGetNoOfTravellers() {
    assertEquals(2, booking.getNoOfTravellers());
  }

  @Test
  void testGetNoOfTotalPrice() {
    assertEquals(1500.0, booking.getTotalPrice());
  }

  @Test
  void testGetPackageId() {
    assertEquals(201, booking.getPackageId());
  }

  @Test
  void testGetEventId() {
    assertEquals(1, booking.getEventId());
  }
>>>>>>> devBranch
}
