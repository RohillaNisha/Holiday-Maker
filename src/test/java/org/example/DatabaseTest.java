package org.example;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    private Database db;

  @BeforeEach
    void setUp() {
      db = new Database();
  }

  @AfterEach
    void tearDown(){

  }

  @Test
    void testCreateNewUserAndGetAllUsers(){

      db.createNewUser("test", "test", "test@test.com" , "98741236", "TT4124", "Lund", LocalDate.of(1968,06,07));
      ArrayList<User> users = db.listOfAllUsers();

      boolean newUserFound = false;
      for (User user: users){
          if(user.getFirstName().equals("test")&& user.getLastName().equals("test") ){
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
            if (event.getEventName() == "Concert") {
                eventFound = true;
                break;
            }
        }

        assertTrue(eventFound, "Event not found in the list.");
    }
}