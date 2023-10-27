package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoomsTest {

    Rooms rooms;

    @BeforeEach
    void setUp(){
        this.rooms = new Rooms();
    }

    @Test
    void testGetRoomType(){
        Assertions.assertEquals(rooms.getRoomType(), "OneBed");
    }

    @Test
    void testMostExpensiveRoom(){
        Assertions.assertEquals(rooms.getMostExpensiveRoom(), 2000);
    }

    @Test
    void testGetNumberOfRooms(){
        Assertions.assertEquals(rooms.getNumberOfRooms(), 6);
    }

    @Test
    void testGetNumberOfCheapRooms(){
        Assertions.assertEquals(rooms.getNumberOfCheapRooms(), 2);
    }

}
