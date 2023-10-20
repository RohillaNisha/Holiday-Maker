package org.example.booking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class BookingTest {

  Booking booking = new Booking(101, LocalDate.parse("2023-10-20"), true);

  @Test
  void testGetBookingId() {
    assertEquals(101, booking.getId());
  }

  @Test
  void testGetBookingDate() {
    assertEquals(LocalDate.parse("2023-10-20"), booking.getDate());
  }

  @Test
  void testGetBookingStatus() {
    assertTrue(booking.isPaid());
  }
}
