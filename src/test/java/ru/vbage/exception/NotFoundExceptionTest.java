package ru.vbage.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertSame;

class NotFoundExceptionTest {
    @Test
    void testConstructor() {
        NotFoundException actualNotFoundException = new NotFoundException("An error occurred");
        assertNull(actualNotFoundException.getCause());
        assertEquals("ru.vbage.exception.NotFoundException: An error occurred", actualNotFoundException.toString());
        assertEquals(0, actualNotFoundException.getSuppressed().length);
        assertEquals("An error occurred", actualNotFoundException.getMessage());
        assertEquals("An error occurred", actualNotFoundException.getLocalizedMessage());
    }
}

