package ru.vbage.security.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class JwtUserDetailsServiceTest {
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        assertThrows(UsernameNotFoundException.class, () -> this.jwtUserDetailsService.loadUserByUsername("janedoe"));
    }
}

