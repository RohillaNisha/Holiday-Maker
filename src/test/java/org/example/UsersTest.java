package org.example;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UsersTest {


    private Users users;
    String personalNumber = "NR1234";

    @BeforeEach
    public void setUp(){
        users = new Users();
    }

    @AfterEach
    public void cleanUp(){
        users.deleteLastUser();
    }



    @Test
    public void testGetTotalNumberOfUsers(){

        Assertions.assertEquals(users.getTotalNumberOfUsers(), 3);
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

        users.createUser("test", "test", "test@test.com", "987456321", "TT4212", "Staffanstorp", LocalDate.of(1968,06,07));

        int updatedSize = users.getTotalNumberOfUsers();

        assertEquals(initialSize + 1, updatedSize);


    }

    @Test
    public void testSearchUserByPersonalNumber(){

        User fetchedUser = users.searchUserByPersonalNumber(personalNumber);
        System.out.println("fetched test user is: "+ fetchedUser );
        Assertions.assertEquals(fetchedUser.getPersonalNumber(), "NR1234" );

    }







}