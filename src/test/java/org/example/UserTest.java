package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

class UserTest {

    User user = new User(1,"Raj", "Mehra", "rajMehra@gmail.com", "789456123", "RM4545", "Malmö", LocalDate.of(1987,05,18));

    @Test
    public void getId(){
        Assertions.assertEquals(user.getId(), 1);
    }

    @Test
    public void getFirstName() {
        Assertions.assertEquals(user.getFirstName(), "Raj");

    }

    @Test
    public void getLastName() {
        Assertions.assertEquals(user.getLastName(), "Mehra");

    }

    @Test
    public void getEmail() {
        Assertions.assertEquals(user.getEmail(), "rajMehra@gmail.com");

    }





}