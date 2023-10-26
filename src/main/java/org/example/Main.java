package org.example;

import org.example.menu.MenuSystem;

public class Main {
  public static void main(String[] args) {

    System.out.printf("--------------------------------%n");
    System.out.printf("         HOLIDAY MAKER          %n");
    System.out.printf("--------------------------------%n");

    MenuSystem menuSystem = MenuSystem.getInstance();
    while (true) {
      menuSystem.execute();
    }
  }
}
