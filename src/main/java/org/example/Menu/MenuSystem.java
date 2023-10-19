package org.example.Menu;

public class MenuSystem implements MenuState{

    private static MenuState activeMenu;
    private final static MenuSystem menuSystem = new MenuSystem();

    public MenuSystem() {

        activeMenu = new MainMenu("Main Menu");
    }

    public static MenuSystem getInstance(){
        return menuSystem;
    }

    public static void setActiveMenu(MenuState selectedMenu){
        activeMenu = selectedMenu;
    }

    public static MenuState getActiveMenu() {
        return activeMenu;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public void execute() {
        activeMenu.execute();

    }
}