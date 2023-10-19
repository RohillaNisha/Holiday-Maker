package org.example.Menu;

public class MenuSystem implements MenuState{
    private static final MenuSystem menuSystem = new MenuSystem();
    private static MenuState activeMenu;

    public MenuSystem() {
        activeMenu = new MainMenu();
    }

    public static MenuState getActiveMenu() {
        return activeMenu;
    }

    public static void setActiveMenu(MenuState activeMenu) {
        MenuSystem.activeMenu = activeMenu;
    }

    public static MenuSystem getInstance() {
        return menuSystem;
    }
    @Override
    public void execute() {

    }
}
