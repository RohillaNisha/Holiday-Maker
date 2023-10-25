package org.example.booking;

import org.example.Database;
import org.example.Event;
import org.example.Room;
import org.example.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BookingProcessTest {

    private BookingProcess bookingProcess;
    private Database db;


    @BeforeEach
    void setUp() {
        db = new Database();
        bookingProcess = new BookingProcess();

    }

    @Test
    void testSearchUserByPersonalNumber() {
        String personalNumber = "NR1234";
        User searchedUserFromPersonalNumber = bookingProcess.searchUserByPersonalNumber(personalNumber);

        Assertions.assertEquals(searchedUserFromPersonalNumber.getPersonalNumber(), personalNumber);
    }
      @Test
     void testGetUserInputDate() {
        String input = "2023-10-24";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        LocalDate expectedDate = LocalDate.of(2023, 10, 24);
        LocalDate result = bookingProcess.getUserInputDate("Enter a date");

        assertEquals(expectedDate, result);
    }

    @Test
    void testSearchEventByStartDate(){
        LocalDate startDate = LocalDate.of(2023, 10, 17);
        LocalDate endDate = LocalDate.of(2023, 10, 17);
        List<Event> eventList ;

        eventList = bookingProcess.searchEventsByStartDate(startDate, endDate);

        int eventsInEventsList = eventList.size();

        Assertions.assertEquals(1,eventsInEventsList);
    }

    @Test
    public void testReset() {
        Event event = new Event(
                "Hiking",
                200.00,
                2,
                LocalDate.of(2023,10,17),
                LocalDate.of(2023,10,18));

        Room room = new Room(
                4,
                202,
                "Double",
                1000,
                2
        );

        bookingProcess.selectedRoomIds.add(5);
        bookingProcess.selectedEventIds.add(6);
        bookingProcess.selectedEvents.add(event);
        bookingProcess.roomListPickedByUser.add(room);
        bookingProcess.totalPrice = 200;

        bookingProcess.reset();

        List<Integer> selectedRoomIds = bookingProcess.selectedRoomIds;
        List<Integer> selectedEventIds = bookingProcess.selectedEventIds;
        List<Event> selectedEvents = bookingProcess.selectedEvents;
        List<Room> roomListPickedByUser = bookingProcess.roomListPickedByUser;
        double totalPrice = bookingProcess.totalPrice;

        assertEquals(0, selectedRoomIds.size());
        assertEquals(0, selectedEventIds.size());
        assertEquals(0, selectedEvents.size());
        assertEquals(0, roomListPickedByUser.size());
        assertEquals(0, totalPrice);
    }



}
