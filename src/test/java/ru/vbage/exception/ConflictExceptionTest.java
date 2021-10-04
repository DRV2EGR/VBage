package ru.vbage.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertSame;

class ConflictExceptionTest {
    @Test
    void testConstructor() {
        ConflictException actualConflictException = new ConflictException("An error occurred");
        assertNull(actualConflictException.getCause());
        assertEquals("ru.vbage.exception.ConflictException: An error occurred", actualConflictException.toString());
        assertEquals(0, actualConflictException.getSuppressed().length);
        assertEquals("An error occurred", actualConflictException.getMessage());
        assertEquals("An error occurred", actualConflictException.getLocalizedMessage());
    }
}

