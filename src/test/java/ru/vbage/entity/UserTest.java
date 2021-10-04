package ru.vbage.entity;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class UserTest {
    @Test
    void testConstructor() {
        assertEquals("User{id=0, email='jane.doe@example.org'}",
                (new User("Jane", "Second Name", "Doe", "jane.doe@example.org", "4105551212")).toString());
    }

    @Test
    void testConstructor2() {
        User actualUser = new User(123L, "janedoe");

        assertEquals("janedoe", actualUser.getUsername());
        assertEquals(123L, actualUser.getId());
    }
}

