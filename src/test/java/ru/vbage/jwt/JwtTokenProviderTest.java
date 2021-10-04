package ru.vbage.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.vbage.entity.RefreshToken;
import ru.vbage.repository.RefreshTokenRepository;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class JwtTokenProviderTest {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private RefreshTokenRepository refreshTokenRepository;

    @Test
    void testCreateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken("ABC123");
        refreshToken.setUserId(123L);
        when(this.refreshTokenRepository.save((RefreshToken) any())).thenReturn(refreshToken);
        this.jwtTokenProvider.createRefreshToken(1L);
        verify(this.refreshTokenRepository).save((RefreshToken) any());
    }

    @Test
    void testResolveAccessToken() {
        assertNull(this.jwtTokenProvider.resolveAccessToken(new MockHttpServletRequest()));
        assertNull(this.jwtTokenProvider
                .resolveAccessToken(new MockHttpServletRequest("https://example.org/example", "https://example.org/example")));
    }

    @Test
    void testValidateAccessToken() {
        assertFalse(this.jwtTokenProvider.validateAccessToken("ABC123"));
        assertFalse(this.jwtTokenProvider.validateAccessToken(""));
        assertFalse(this.jwtTokenProvider.validateAccessToken("io.jsonwebtoken.Claims"));
        assertFalse(this.jwtTokenProvider.validateAccessToken("ru.vbage.entity.User"));
    }

    @Test
    void testValidateRefreshToken() {
        assertFalse(this.jwtTokenProvider.validateRefreshToken("ABC123"));
        assertFalse(this.jwtTokenProvider.validateRefreshToken(""));
        assertFalse(this.jwtTokenProvider.validateRefreshToken("io.jsonwebtoken.Claims"));
        assertFalse(this.jwtTokenProvider.validateRefreshToken("ru.vbage.entity.User"));
    }

    @Test
    void testRefreshPairOfTokens() {
        assertThrows(IllegalArgumentException.class, () -> this.jwtTokenProvider.refreshPairOfTokens("ABC123"));
        assertThrows(IllegalArgumentException.class, () -> this.jwtTokenProvider.refreshPairOfTokens(""));
        assertThrows(IllegalArgumentException.class,
                () -> this.jwtTokenProvider.refreshPairOfTokens("io.jsonwebtoken.Claims"));
        assertThrows(IllegalArgumentException.class,
                () -> this.jwtTokenProvider.refreshPairOfTokens("ru.vbage.entity.User"));
    }
}

