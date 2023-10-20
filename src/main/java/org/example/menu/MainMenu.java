package org.example.menu;

import org.example.Database;
import org.example.User;
import org.example.booking.Bookings;

import java.util.List;
import java.util.Scanner;

public class MainMenu extends Menu{


    public MainMenu(String menuName) {
        super(menuName);
        menuOptions =
                List.of(
                        new MenuOption(1, "Book A Journey", () -> {}),
                        new MenuOption(2, "Create your own trip", Bookings::searchUserByPersonalNumber),
                        new MenuOption(3, "Find A Booking", () -> {}),
                        new MenuOption(4, "Exit", () -> {}));
    }



}

