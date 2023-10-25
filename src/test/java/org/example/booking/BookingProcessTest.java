package org.example.booking;

import org.example.Database;
import org.example.Event;
import org.example.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Date;
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

}
