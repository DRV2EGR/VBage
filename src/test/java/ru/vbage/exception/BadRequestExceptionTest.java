package ru.vbage.exception;

import org.junit.jupiter.api.Test;

class BadRequestExceptionTest {
    @Test
    void testConstructor() {
        assertEquals("BadRequestException{}", (new BadRequestException("An error occurred")).toString());
    }
}

