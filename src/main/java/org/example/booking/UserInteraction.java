package org.example.booking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserInteraction {

  private final Scanner scanner = new Scanner(System.in);

  public String getUserInput(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine();
  }

  public LocalDate getUserInputDate(String prompt) {
    System.out.print(prompt + " (yyyy-MM-dd): ");
    String inputDate = scanner.nextLine();
    return LocalDate.parse(inputDate, DateTimeFormatter.ISO_LOCAL_DATE);
  }

  public int getUserInputInt(String prompt) {
    System.out.print(prompt);
    return Integer.parseInt(scanner.nextLine());
  }

  public void displayMessage(String message) {
    System.out.println(message);
  }
}
