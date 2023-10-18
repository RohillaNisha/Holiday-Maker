package org.example;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UsersTest {


    private Users users;

    @BeforeEach
    public void setUp(){
        users = new Users();
    }


    @Test
    public void testGetTotalNumberOfUsers(){

        Assertions.assertEquals(users.getTotalNumberOfUsers(), 2);
    }

    @Test
    public void testGetFirstUsersFirstName(){
        Assertions.assertEquals(users.getFirstUsersFirstName(), "Nisha");
    }

    @Test
    public void testGetLastUsersFirstName(){
        Assertions.assertEquals(users.getLastUsersFirstName(), "Zahra");
    }

    @Test
    public void testCreateUser(){

        int initialSize = users.getTotalNumberOfUsers();

        users.createUser("Rasmus", "Davidsson", "rasmus.davidsson@yahoo.com");

        int updatedSize = users.getTotalNumberOfUsers();

        assertEquals(initialSize + 1, updatedSize);


    }





}