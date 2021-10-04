package ru.vbage.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertSame;

class MessageTest {
    @Test
    void testCanEqual() {
        assertFalse((new Message()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        Message message = new Message();

        Role role = new Role();
        User user = new User();

        Role role1 = new Role();
        User user1 = new User();

        Message message1 = new Message();
        message1.setId(123L);
        message1.setMsg_body("Not all who wander are lost");
        message1.setSender(user);
        message1.setReciever(user1);
        assertTrue(message.canEqual(message1));
    }

    @Test
    void testConstructor() {
        Message actualMessage = new Message();
        actualMessage.setMsg_body("Not all who wander are lost");
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
        actualMessage.setReciever(user);
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
        actualMessage.setSender(user1);
        assertEquals("Not all who wander are lost", actualMessage.getMsg_body());
        User reciever = actualMessage.getReciever();
        assertSame(user, reciever);
        User sender = actualMessage.getSender();
        assertEquals(sender, reciever);
        assertSame(user1, sender);
        assertEquals(user, sender);
        assertEquals("Message(msg_body=Not all who wander are lost, sender=User{id=123, email='jane.doe@example.org'},"
                + " reciever=User{id=123, email='jane.doe@example.org'})", actualMessage.toString());
    }

    @Test
    void testEquals() {
        Role role = new Role();
        User user = new User();

        Role role1 = new Role();
        User user1 = new User();

        Message message = new Message();
        message.setId(123L);
        message.setMsg_body("Not all who wander are lost");
        message.setSender(user);
        message.setReciever(user1);
        assertFalse(message.equals(null));
    }

    @Test
    void testEquals2() {
        Role role = new Role();
        User user = new User();

        Role role1 = new Role();
        User user1 = new User();

        Message message = new Message();
        message.setId(123L);
        message.setMsg_body("Not all who wander are lost");
        message.setSender(user);
        message.setReciever(user1);
        assertFalse(message.equals("Different type to Message"));
    }
}

