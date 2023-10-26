package org.example.booking;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class UserInteractionTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private UserInteraction userInteraction;
  private ByteArrayInputStream inputStream;

  @Test
  void testGetUserInput() {
    inputStream = new ByteArrayInputStream("Test Input".getBytes());
    System.setIn(inputStream);

    userInteraction = new UserInteraction();
    String userInput = userInteraction.getUserInput("Enter something: ");
    assertEquals("Test Input", userInput);
  }

  @Test
  void testGetUserInputInt() {
    inputStream = new ByteArrayInputStream("4".getBytes());
    System.setIn(inputStream);

    userInteraction = new UserInteraction();
    int userInt = userInteraction.getUserInputInt("Enter an integer: ");
    assertEquals(4, userInt);
  }

  @Test
  void testDisplayMessage() {
    userInteraction = new UserInteraction();

    System.setOut(new PrintStream(outContent));

    userInteraction.displayMessage("This is a test message.");
    String output = outContent.toString().trim();
    assertEquals("This is a test message.", output);
  }
}
