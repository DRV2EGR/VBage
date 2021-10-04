package ru.vbage.entity;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class RefreshTokenTest {
    @Test
    void testConstructor() {
        RefreshToken actualRefreshToken = new RefreshToken(123L, "ABC123");

        assertEquals("ABC123", actualRefreshToken.getRefreshToken());
        assertEquals(123L, actualRefreshToken.getUserId());
    }
}

