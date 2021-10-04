package ru.vbage.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.vbage.dto.AuthenticationRequestDto;
import ru.vbage.dto.JwtAuthDto;
import ru.vbage.entity.Role;
import ru.vbage.entity.User;
import ru.vbage.security.jwt.JwtTokenProvider;
import ru.vbage.service.UserService;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AuthenticationController.class})
@ExtendWith(SpringExtension.class)
class AuthenticationControllerTest {
    @Autowired
    private AuthenticationController authenticationController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private UserService userService;

    @Test
    void testLogin() throws Exception {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setRole(role);
        user.setActivationCode("Activation Code");
        user.setCreatedActivationCode(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setId(123L);
        user.setFriends(new ArrayList<User>());
        user.setPhoneNumber("4105551212");
        user.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserProfileImageUrl("https://example.org/example");
        user.setFirstName("Jane");
        user.setUsername("janedoe");
        user.setSecondName("Second Name");
        Optional<User> ofResult = Optional.<User>of(user);
        when(this.userService.findByEmail((String) any())).thenReturn(ofResult);
        when(this.jwtTokenProvider.createRefreshToken(anyLong())).thenReturn("ABC123");
        when(this.jwtTokenProvider.createAccessToken((User) any())).thenReturn("ABC123");
        when(this.authenticationManager.authenticate((org.springframework.security.core.Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail("jane.doe@example.org");
        authenticationRequestDto.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(authenticationRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"username\":\"janedoe\",\"accessToken\":\"ABC123\",\"refreshToken\":\"ABC123\"}"));
    }

    @Test
    void testLogin2() throws Exception {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setLastName("Doe");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setRole(role);
        user.setActivationCode("Activation Code");
        user.setCreatedActivationCode(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setId(123L);
        user.setFriends(new ArrayList<User>());
        user.setPhoneNumber("4105551212");
        user.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserProfileImageUrl("https://example.org/example");
        user.setFirstName("Jane");
        user.setUsername("janedoe");
        user.setSecondName("Second Name");
        Optional<User> ofResult = Optional.<User>of(user);
        when(this.userService.findByEmail((String) any())).thenReturn(ofResult);
        when(this.jwtTokenProvider.createRefreshToken(anyLong())).thenThrow(new IllegalArgumentException("foo"));
        when(this.jwtTokenProvider.createAccessToken((User) any())).thenReturn("ABC123");
        when(this.authenticationManager.authenticate((org.springframework.security.core.Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail("jane.doe@example.org");
        authenticationRequestDto.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(authenticationRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.authenticationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testLogin3() throws Exception {
        when(this.userService.findByEmail((String) any())).thenReturn(Optional.<User>empty());
        when(this.jwtTokenProvider.createRefreshToken(anyLong())).thenReturn("ABC123");
        when(this.jwtTokenProvider.createAccessToken((User) any())).thenReturn("ABC123");
        when(this.authenticationManager.authenticate((org.springframework.security.core.Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setEmail("jane.doe@example.org");
        authenticationRequestDto.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(authenticationRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.authenticationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void testRefresh() throws Exception {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setRefreshToken("ABC123");
        jwtAuthDto.setAccessToken("ABC123");
        jwtAuthDto.setUsername("janedoe");
        when(this.jwtTokenProvider.refreshPairOfTokens((String) any())).thenReturn(jwtAuthDto);

        JwtAuthDto jwtAuthDto1 = new JwtAuthDto();
        jwtAuthDto1.setRefreshToken("ABC123");
        jwtAuthDto1.setAccessToken("ABC123");
        jwtAuthDto1.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(jwtAuthDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/api/auth/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"username\":\"janedoe\",\"accessToken\":\"ABC123\",\"refreshToken\":\"ABC123\"}"));
    }
}

