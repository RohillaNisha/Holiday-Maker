package org.example.Menu;

import org.example.Database;
import org.example.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {

    private Database db = new Database();

    @Test

    public void testSearchUserByPersonalNumber() {
       String personalNumber = "NR1234";
       User fetchedUser = db.searchedUserByPersonalNumber(personalNumber);
      Assertions.assertEquals(fetchedUser.getPersonalNumber(), "NR1234");

    }

}