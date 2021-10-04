package ru.vbage.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import ru.vbage.service.UserService;

import static org.mockito.Mockito.*;

class JwtTokenFilterTest {

    @Test
    void testDoFilter() throws IOException, ServletException {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(
                new JwtTokenProvider(new JwtUserDetailsService(new UserService())));
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        Response servletResponse = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter((javax.servlet.ServletRequest) any(), (javax.servlet.ServletResponse) any());
        jwtTokenFilter.doFilter(servletRequest, servletResponse, filterChain);
        verify(filterChain).doFilter((javax.servlet.ServletRequest) any(), (javax.servlet.ServletResponse) any());
    }
}

