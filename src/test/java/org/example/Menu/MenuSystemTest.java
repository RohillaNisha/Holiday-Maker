package org.example.Menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MenuSystemTest {
    private final MenuSystem menuSystem = MenuSystem.getInstance();
    static Stream<MenuState> menuStateProvider() {
    return Stream.of(new MainMenu(), new PackagesMenu());
    }

    @Test
    @DisplayName("Creating instance of menu system successfully")
    public void testGetInstance() {
        MenuSystem instance = MenuSystem.getInstance();
        Assertions.assertNotNull(instance);
        assertEquals(menuSystem, instance);
    }

    @Test
    @DisplayName("Main menu is the initial state")
    public void testInitialStateIsMainMenu() {
        MenuState activeMenu = MenuSystem.getActiveMenu();
        Assertions.assertNotNull(activeMenu);
        assertEquals(MainMenu.class, activeMenu.getClass());
    }

    @ParameterizedTest
    @MethodSource("menuStateProvider")
    @DisplayName("Menu system change the active state correctly")
    public void testSetActiveMenu(MenuState menu) {
        MenuSystem.setActiveMenu(menu);
        MenuState activeMenu = MenuSystem.getActiveMenu();
        assertEquals(menu.getClass(), activeMenu.getClass());
        MenuSystem.setActiveMenu(new MainMenu());
    }
}
