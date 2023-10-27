package org.example.menu;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@org.junit.jupiter.api.TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class MenuOptionTest {

  @Mock private Runnable mockAction;

  Stream<Arguments> menuOptionProvider() {
    return Stream.of(
        Arguments.of(new MenuOption(1, "Book A Journey", mockAction), 1, "Book A Journey"),
        Arguments.of(new MenuOption(2, "Create A Journey", mockAction), 2, "Create A Journey"),
        Arguments.of(new MenuOption(3, "Find A Booking", mockAction), 3, "Find A Booking"),
        Arguments.of(new MenuOption(4, "Exit", mockAction), 4, "Exit"));
  }

  @ParameterizedTest
  @MethodSource("menuOptionProvider")
  @DisplayName("Getting option`s number successfully")
  void testGetOptionNumber(MenuOption menuOption, int expectedOptionNumber) {
    int optionNumber = menuOption.getOptionNumber();
    assertEquals(expectedOptionNumber, optionNumber);
  }

  @ParameterizedTest
  @MethodSource("menuOptionProvider")
  @DisplayName("Getting option`s text successfully")
  void testGetOptionText(MenuOption menuOption, int optionNumber, String expectedOptionText) {
    String optionText = menuOption.getOptionText();
    assertEquals(expectedOptionText, optionText);
  }

  @ParameterizedTest
  @MethodSource("menuOptionProvider")
  @DisplayName("performAction for MenuOption successfully")
  void testPerformAction() {
    mockAction.run();
    verify(mockAction, times(1)).run();
  }
}
