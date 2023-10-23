package org.example.booking;
import org.example.Database;
import org.example.Event;
import org.example.Room;
import org.example.User;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingProcess {

    private static Database db = new Database();
    private static List<Event> selectedEvents = new ArrayList<>();
    private static List<Event> eventListPickedByUser;
    private static List<Room> roomList;
    private static List<Room> roomListPickedByUser = new ArrayList<>();

     static Scanner input = new Scanner(System.in);

     public static void createABooking(){

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
         searchEventsByStartDate(startDate, endDate);
         System.out.println("Select id's of the events (comma separated) that you want to add");
         String eventsSelected = input.nextLine();
         String[] eventIds = eventsSelected.split(",");

         for(String eventId : eventIds){
             for(int i = 0; i < eventListPickedByUser.size(); i++){
                 if(eventListPickedByUser.get(i).getEventId() == Integer.parseInt(eventId)){
                     selectedEvents.add(eventListPickedByUser.get(i));
                 }
             }
         }
         System.out.println("Enter number of travelers");
         int noOfTravelers = input.nextInt();
         input.nextLine();
         roomList = db.listOfAllRooms();
         System.out.println(roomList);
         System.out.println("Please select rooms (comma separated)");
         String roomsSelected = input.nextLine();
         String[] roomsIds = roomsSelected.split(",");

         for(String roomId : roomsIds){
             for(int i = 0; i < roomList.size(); i++){
                 if(roomList.get(i).getId() == Integer.parseInt(roomId)){
                     roomListPickedByUser.add(roomList.get(i));
                 }
             }
         }
         int totalCapacity = 0;
         for(Room room : roomListPickedByUser){
             totalCapacity += room.getRoomCapacity();
         }
         if(noOfTravelers > totalCapacity){
             System.out.println("You need more rooms");
             System.out.println(roomList);
             int newRoomAdded = input.nextInt();
             input.nextLine();
             for(Room room : roomList){
                 if(room.getId() == newRoomAdded){
                     roomListPickedByUser.add(room);
                 }
             }
         }
         System.out.println("Updated: " + roomListPickedByUser);



     }

     public static User searchUserByPersonalNumber(String personalNumber){
         User fetchedUser = db.searchedUserByPersonalNumber(personalNumber);
         return fetchedUser;
     }

     public static List<Event> searchEventsByStartDate(LocalDate startDate, LocalDate endDate){

         eventListPickedByUser = db.listOfEventsByStartDate(Date.valueOf(startDate), Date.valueOf(endDate));
         displayFetchedEvents(eventListPickedByUser);
         return eventListPickedByUser;
     }

     public static void displayFetchedEvents(List<Event> eventList){
         System.out.println("No. Event id         Date       Price    Event name");
         for(int i = 0; i < eventList.size(); i++){

             System.out.println(i + 1 + ".) " + eventList.get(i).getEventId() +  "    " + eventList.get(i).getStartDate() + "      " + eventList.get(i).getEventPrice() + " " + eventList.get(i).getEventName() );
         }
     }

}
