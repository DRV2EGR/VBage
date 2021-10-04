package ru.vbage.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.vbage.dto.UserPublicDto;
import ru.vbage.entity.Role;
import ru.vbage.entity.User;
import ru.vbage.service.ClassToDtoService;
import ru.vbage.service.UserService;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserPublicController.class})
@ExtendWith(SpringExtension.class)
class UserPublicControllerTest {
    @MockBean
    private ClassToDtoService classToDtoService;

    @Autowired
    private UserPublicController userPublicController;

    @MockBean
    private UserService userService;

    @Test
    void testGetPublicUserInfoById() throws Exception {
        when(this.classToDtoService.convertUserToUserPublicDto((User) any())).thenReturn(
                new UserPublicDto(123L, "Jane", "Second Name", "Doe", "janedoe", "jane.doe@example.org", "4105551212"));

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
        when(this.userService.findUserById(anyLong())).thenReturn(user);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/user/public/user_by_id");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.userPublicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"firstName\":\"Jane\",\"secondName\":\"Second Name\",\"lastName\":\"Doe\",\"username\":\"janedoe\",\"email"
                                        + "\":\"jane.doe@example.org\",\"phoneNumber\":\"4105551212\"}"));
    }

    @Test
    void testGetPublicUserInfoByUsername() throws Exception {
        when(this.classToDtoService.convertUserToUserPublicDto((User) any())).thenReturn(
                new UserPublicDto(123L, "Jane", "Second Name", "Doe", "janedoe", "jane.doe@example.org", "4105551212"));

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
        when(this.userService.findUserByUsername((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/api/user/public/user_by_username")
                .param("username", "foo");
        MockMvcBuilders.standaloneSetup(this.userPublicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"firstName\":\"Jane\",\"secondName\":\"Second Name\",\"lastName\":\"Doe\",\"username\":\"janedoe\",\"email"
                                        + "\":\"jane.doe@example.org\",\"phoneNumber\":\"4105551212\"}"));
    }
}

