package org.example.booking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
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
  }

  @Test
  void testGetBookingDate() {
    assertEquals(LocalDate.now(), booking.getBookingDate());
  }

  @Test
  void testGetPaidStatus() {
    assertTrue(booking.isPaid());
  }

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
}