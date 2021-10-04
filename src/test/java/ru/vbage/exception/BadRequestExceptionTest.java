package ru.vbage.exception;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertSame;

class BadRequestExceptionTest {
    @Test
    void testConstructor() {
        assertEquals("BadRequestException{}", (new BadRequestException("An error occurred")).toString());
    }
}

