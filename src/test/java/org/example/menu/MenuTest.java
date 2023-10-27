package org.example.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class MenuTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inContent =
            new ByteArrayInputStream("5\n".getBytes());

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    public void testExecuteWithValidOption() {
        Menu menu = new Menu("Main Menu");
        MenuOption menuOption1 = mock(MenuOption.class);
        when(menuOption1.getOptionNumber()).thenReturn(1);
        when(menuOption1.getOptionText()).thenReturn("Book A Journey");
        MenuOption menuOption2 = mock(MenuOption.class);
        when(menuOption2.getOptionNumber()).thenReturn(2);
        when(menuOption2.getOptionText()).thenReturn("Create A Journey");
        menu.menuOptions = List.of(menuOption1, menuOption2);

        menu.execute();

        String expectedOutput = "Main Menu\n1.) Book A Journey\n2.) Create A Journey\n";
        assertEquals(expectedOutput, outContent.toString());
    }

//  @Test
//  @Timeout(5)
//  public void testExecuteWithInvalidOption() {
//    Menu menu = new Menu("Main Menu");
//    MenuOption menuOption1 = mock(MenuOption.class);
//    when(menuOption1.getOptionNumber()).thenReturn(1);
//    when(menuOption1.getOptionText()).thenReturn("Book A Journey");
//    MenuOption menuOption2 = mock(MenuOption.class);
//    when(menuOption2.getOptionNumber()).thenReturn(2);
//    when(menuOption2.getOptionText()).thenReturn("Create A Journey");
//    menu.menuOptions = List.of(menuOption1, menuOption2);
//
//    menu.execute();
//
//    String expectedOutput = "Main Menu\n1.) Book A Journey\n2.) Create A Journey\n" +
//            "Please choose a valid option number between 1 to 4.\n";
//    assertEquals(expectedOutput, outContent.toString());
//  }
}