package org.example.menu;

import org.example.Database;
import org.example.User;
import org.example.booking.BookingProcess;

import java.util.List;
import java.util.Scanner;

public class MainMenu extends Menu{


    public MainMenu(String menuName) {
        super(menuName);
        menuOptions =
                List.of(
                        new MenuOption(1, "Create a new Customer", () -> {}),
                        new MenuOption(2, "Create your own trip", BookingProcess::createABooking),
                        new MenuOption(3, "Find A Booking", () -> {}),
                        new MenuOption(4, "Exit", () -> {}));
    }



}

