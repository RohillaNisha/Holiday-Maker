package org.example.menu;

import java.util.List;
import org.example.Database;
import org.example.Users;
import org.example.booking.BookingProcess;
import org.example.booking.BookingService;
import org.example.booking.EventBookingProcess;
import org.example.booking.UserInteraction;

public class MainMenu extends Menu{


    private final Database db = new Database();
    BookingProcess bookingProcess = new BookingProcess();
    BookingService bookingService = new BookingService(db);
    UserInteraction userInteraction = new UserInteraction();
    EventBookingProcess eventBookingProcess =new EventBookingProcess(bookingService, userInteraction);
    public MainMenu(String menuName) {
        super(menuName);
    menuOptions =
        List.of(
            new MenuOption(1, "Create a new Customer", Users::createAUser),
            new MenuOption(2, "Create your own trip", eventBookingProcess::createABooking),
            new MenuOption(3, "Find A Booking", bookingProcess::findABooking),
            new MenuOption(4, "Change paid status", bookingProcess::changePaidStatus),
            new MenuOption(5, "Exit", () -> System.exit(0)));
    }



}
