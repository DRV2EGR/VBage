package ru.vbage.exception;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JwtInvalidAccessTokenExceptionTest {
    @Test
    void testConstructor() {
        JwtInvalidAccessTokenException actualJwtInvalidAccessTokenException = new JwtInvalidAccessTokenException(
                "An error occurred");
        assertNull(actualJwtInvalidAccessTokenException.getCause());
        assertEquals("BadRequestException{}", actualJwtInvalidAccessTokenException.toString());
        assertEquals(0, actualJwtInvalidAccessTokenException.getSuppressed().length);
        assertEquals("An error occurred", actualJwtInvalidAccessTokenException.getMessage());
        assertEquals("An error occurred", actualJwtInvalidAccessTokenException.getLocalizedMessage());
    }
}

