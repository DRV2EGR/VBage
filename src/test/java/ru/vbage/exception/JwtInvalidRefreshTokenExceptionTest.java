package ru.vbage.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class JwtInvalidRefreshTokenExceptionTest {
    @Test
    void testConstructor() {
        JwtInvalidRefreshTokenException actualJwtInvalidRefreshTokenException = new JwtInvalidRefreshTokenException(
                "An error occurred");
        assertNull(actualJwtInvalidRefreshTokenException.getCause());
        assertEquals("BadRequestException{}", actualJwtInvalidRefreshTokenException.toString());
        assertEquals(0, actualJwtInvalidRefreshTokenException.getSuppressed().length);
        assertEquals("An error occurred", actualJwtInvalidRefreshTokenException.getMessage());
        assertEquals("An error occurred", actualJwtInvalidRefreshTokenException.getLocalizedMessage());
    }
}

