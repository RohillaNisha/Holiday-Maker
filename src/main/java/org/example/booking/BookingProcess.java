package org.example.booking;
import org.example.Database;
import org.example.Event;
import org.example.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingProcess {

    private static Database db = new Database();

     Scanner input = new Scanner(System.in);

     public void createABooking(){

         System.out.println("Enter customer's personal number: ");
         String personalNumber = input.nextLine();
          User fetchedUser = searchUserByPersonalNumber(personalNumber);
          if(fetchedUser == null){
             System.out.println("This is a new customer. Create the customer first.");
             return;
         }
         System.out.println("Enter start date for your trip: (yyyy-mm-dd) ");
         LocalDate startDate = LocalDate.parse(input.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
         System.out.println("Enter end date for your trip: (yyyy-mm-dd) ");
         LocalDate endDate = LocalDate.parse(input.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);


     }

     public User searchUserByPersonalNumber(String personalNumber){
         User fetchedUser = db.searchedUserByPersonalNumber(personalNumber);
         return fetchedUser;
     }

     public ArrayList<Event> searchEventsByStartDate(LocalDate startDate){

     }


}
