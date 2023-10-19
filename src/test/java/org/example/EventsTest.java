package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventsTest {

    Events events;

    @BeforeEach
    void setUp(){
        this.events= new Events();

    }

    @Test
    void getEventName(){
        Assertions.assertEquals(events.getEventName(),"eventName");
    }

    @Test
    void getEventPrice(){
        events.createEvent("Deep sea diving in Lofoten",1500);
        Assertions.assertEquals(events.getEventPrice(),"Deep sea diving in Lofoten");
    }



}
