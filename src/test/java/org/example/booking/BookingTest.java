package org.example.booking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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
  }

  @Test
  void testGetBookingDate() {
    assertEquals(LocalDate.parse("2023-10-20"), booking.getBookingDate());
  }

  @Test
  void testGetBookingStatus() {
    assertTrue(booking.isPaid());
  }


  @Test

  public void testSearchUserByPersonalNumber() {
    String personalNumber = "NR1234";
    User fetchedUser = db.searchedUserByPersonalNumber(personalNumber);
    Assertions.assertEquals(fetchedUser.getPersonalNumber(), "NR1234");

  }

}
