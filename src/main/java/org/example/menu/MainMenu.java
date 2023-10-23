package org.example.menu;

import org.example.Users;

import java.util.List;

public class MainMenu extends Menu{
    public MainMenu(String menuName) {
        super(menuName);
        menuOptions =
                List.of(
                        new MenuOption(1, "create a new customer", Users::createAUser),
                        new MenuOption(2, "Create A Journey", () -> {}),
                        new MenuOption(3, "Find A Booking", () -> {}),
                        new MenuOption(4, "Exit", () -> {}));
    }

}
