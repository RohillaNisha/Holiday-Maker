package org.example.Menu;

public class MenuOption {
  private final int optionNumber;
  private final String optionText;
  private final Runnable action;

  public MenuOption(int optionNumber, String text, Runnable action) {
    this.optionNumber = optionNumber;
    this.optionText = text;
    this.action = action;
  }

  public int getOptionNumber() {
    return optionNumber;
  }

  public String getOptionText() {
    return optionText;
  }

  public void performAction() {
    action.run();
  }
}
