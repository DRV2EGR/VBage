package ru.vbage.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.vbage.dto.MessageDto;
import ru.vbage.dto.UserPublicDto;
import ru.vbage.entity.Message;
import ru.vbage.entity.Role;
import ru.vbage.entity.User;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ClassToDtoServiceTest {
    @Autowired
    private ClassToDtoService classToDtoService;

    @Test
    void testConvertUserToUserPublicDto() {
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
        UserPublicDto actualConvertUserToUserPublicDtoResult = this.classToDtoService.convertUserToUserPublicDto(user);
        assertEquals("jane.doe@example.org", actualConvertUserToUserPublicDtoResult.getEmail());
        assertEquals("janedoe", actualConvertUserToUserPublicDtoResult.getUsername());
        assertEquals("Second Name", actualConvertUserToUserPublicDtoResult.getSecondName());
        assertEquals("4105551212", actualConvertUserToUserPublicDtoResult.getPhoneNumber());
        assertEquals("Doe", actualConvertUserToUserPublicDtoResult.getLastName());
        assertEquals(123L, actualConvertUserToUserPublicDtoResult.getId());
        assertEquals("Jane", actualConvertUserToUserPublicDtoResult.getFirstName());
    }

    @Test
    void testConvertMessageToMessageDto() {
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

        Role role1 = new Role();
        role1.setId(123L);
        role1.setName("Name");

        User user1 = new User();
        user1.setLastName("Doe");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setRole(role1);
        user1.setActivationCode("Activation Code");
        user1.setCreatedActivationCode(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setId(123L);
        user1.setFriends(new ArrayList<User>());
        user1.setPhoneNumber("4105551212");
        user1.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUserProfileImageUrl("https://example.org/example");
        user1.setFirstName("Jane");
        user1.setUsername("janedoe");
        user1.setSecondName("Second Name");

        Message message = new Message();
        message.setId(123L);
        message.setMsg_body("Not all who wander are lost");
        message.setSender(user);
        message.setReciever(user1);
        MessageDto actualConvertMessageToMessageDtoResult = this.classToDtoService.convertMessageToMessageDto(message);
        assertEquals("Not all who wander are lost", actualConvertMessageToMessageDtoResult.getBody());
        assertEquals(123L, actualConvertMessageToMessageDtoResult.getSender_id().longValue());
    }
}

