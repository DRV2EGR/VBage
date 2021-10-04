package ru.vbage.controller;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.vbage.dto.UserDto;
import ru.vbage.payload.SendMessagePayload;
import ru.vbage.payload.UserDtoPayload;
import ru.vbage.service.UserService;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserPrivateController.class})
@ExtendWith(SpringExtension.class)
class UserPrivateControllerTest {
    @Autowired
    private UserPrivateController userPrivateController;

    @MockBean
    private UserService userService;

    @Test
    void testAddFriend() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/user/private/add_friend");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("friend_id", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userPrivateController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testAddFriend2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/user/private/add_friend", "Uri Vars");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("friend_id", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userPrivateController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testChangeUserInfo() throws Exception {
        when(this.userService.changeInfo((ru.vbage.entity.User) any(), (UserDtoPayload) any()))
                .thenReturn(new UserDto("Jane", "Second Name", "Doe", "janedoe", "jane.doe@example.org", "4105551212", "Role"));

        UserDtoPayload userDtoPayload = new UserDtoPayload();
        userDtoPayload.setLastName("Doe");
        userDtoPayload.setEmail("jane.doe@example.org");
        userDtoPayload.setPassword("iloveyou");
        userDtoPayload.setUsername("janedoe");
        userDtoPayload.setSecondName("Second Name");
        userDtoPayload.setPhoneNumber("4105551212");
        userDtoPayload.setFirstName("Jane");
        userDtoPayload.setUserProfileImageUrl("https://example.org/example");
        String content = (new ObjectMapper()).writeValueAsString(userDtoPayload);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v1/api/user/private/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userPrivateController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"firstName\":\"Jane\",\"secondName\":\"Second Name\",\"lastName\":\"Doe\",\"username\":\"janedoe\",\"email\":"
                                        + "\"jane.doe@example.org\",\"phoneNumber\":\"4105551212\",\"role\":\"Role\"}"));
    }

    @Test
    void testChangeUserInfo2() throws Exception {
        UserDtoPayload userDtoPayload = new UserDtoPayload();
        userDtoPayload.setLastName("Doe");
        userDtoPayload.setEmail("jane.doe@example.org");
        userDtoPayload.setPassword("iloveyou");
        userDtoPayload.setUsername("janedoe");
        userDtoPayload.setSecondName("Second Name");
        userDtoPayload.setPhoneNumber("4105551212");
        userDtoPayload.setFirstName("Jane");
        userDtoPayload.setUserProfileImageUrl("https://example.org/example");
        String content = (new ObjectMapper()).writeValueAsString(userDtoPayload);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v1/api/user/private/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userPrivateController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testDeleteFriend() throws Exception {
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/v1/api/user/private/delete_friend");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("friend_id", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userPrivateController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetAllFriends() throws Exception {
        when(this.userService.getAllFriends((ru.vbage.entity.User) any())).thenReturn(new ArrayList<UserDto>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/api/user/private/friends");
        MockMvcBuilders.standaloneSetup(this.userPrivateController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllFriends2() throws Exception {
        when(this.userService.getAllFriends((ru.vbage.entity.User) any())).thenReturn(new ArrayList<UserDto>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/user/private/friends");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.userPrivateController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllFriends3() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/api/user/private/friends");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userPrivateController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetAllFriends4() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/user/private/friends");
        getResult.contentType("Not all who wander are lost");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userPrivateController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetAllRecievedMessages() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/api/user/private/get_all_messages");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userPrivateController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetAllRecievedMessages2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/user/private/get_all_messages");
        getResult.contentType("Not all who wander are lost");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userPrivateController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetAllUserInfo() throws Exception {
        when(this.userService.convertUserToUserDto((ru.vbage.entity.User) any()))
                .thenReturn(new UserDto("Jane", "Second Name", "Doe", "janedoe", "jane.doe@example.org", "4105551212", "Role"));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/user/private/user");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.userPrivateController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"firstName\":\"Jane\",\"secondName\":\"Second Name\",\"lastName\":\"Doe\",\"username\":\"janedoe\",\"email\":"
                                        + "\"jane.doe@example.org\",\"phoneNumber\":\"4105551212\",\"role\":\"Role\"}"));
    }

    @Test
    void testGetAllUserInfo2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/api/user/private/user");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userPrivateController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testSendMessage() throws Exception {
        SendMessagePayload sendMessagePayload = new SendMessagePayload();
        sendMessagePayload.setBody("Not all who wander are lost");
        sendMessagePayload.setId_to(1L);
        String content = (new ObjectMapper()).writeValueAsString(sendMessagePayload);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/api/user/private/send_message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userPrivateController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

