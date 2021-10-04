package ru.vbage.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertSame;

class UserNotFoundExeptionTest {
    @Test
    void testConstructor() {
        UserNotFoundExeption actualUserNotFoundExeption = new UserNotFoundExeption("Not all who wander are lost");
        assertNull(actualUserNotFoundExeption.getCause());
        assertEquals("ru.vbage.exception.UserNotFoundExeption: Not all who wander are lost",
                actualUserNotFoundExeption.toString());
        assertEquals(0, actualUserNotFoundExeption.getSuppressed().length);
        assertEquals("Not all who wander are lost", actualUserNotFoundExeption.getMessage());
        assertEquals("Not all who wander are lost", actualUserNotFoundExeption.getLocalizedMessage());
    }
}

