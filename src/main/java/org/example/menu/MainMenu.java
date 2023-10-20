package org.example.menu;

import org.example.Database;
import org.example.User;

import java.util.List;
import java.util.Scanner;

public class MainMenu extends Menu{

    private Database db = new Database();

    Scanner input = new Scanner(System.in);
    public MainMenu(String menuName) {
        super(menuName);
        menuOptions =
                List.of(
                        new MenuOption(1, "Book A Journey", () -> {}),
                        new MenuOption(2, "Create your own trip", () -> {searchUserByPersonalNumber();}),
                        new MenuOption(3, "Find A Booking", () -> {}),
                        new MenuOption(4, "Exit", () -> {}));
    }

    private User searchUserByPersonalNumber() {
        System.out.println("Enter customer's personal number: ");
        String personalNumber = input.nextLine();
        User fetchedUser = db.searchedUserByPersonalNumber(personalNumber);
        if(fetchedUser == null){
            System.out.println("This is a new customer. Create the customer first.");
            return null;
        }
        return fetchedUser;
    }

}

