package ru.vbage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.vbage.dto.MessageDto;
import ru.vbage.dto.UserDto;
import ru.vbage.entity.Message;
import ru.vbage.entity.Role;
import ru.vbage.entity.User;
import ru.vbage.exception.UserNotFoundExeption;
import ru.vbage.payload.UserDtoPayload;
import ru.vbage.repository.MessageRepository;
import ru.vbage.repository.RoleRepository;
import ru.vbage.repository.UserRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void testFindByUsername() {
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
        when(this.userRepository.findByUsername((String) any())).thenReturn(ofResult);
        Optional<User> actualFindByUsernameResult = this.userService.findByUsername("janedoe");
        assertSame(ofResult, actualFindByUsernameResult);
        assertTrue(actualFindByUsernameResult.isPresent());
        verify(this.userRepository, atLeast(1)).findByUsername((String) any());
    }

    @Test
    void testFindByUsername2() {
        Optional<User> emptyResult = Optional.<User>empty();
        when(this.userRepository.findByUsername((String) any())).thenReturn(emptyResult);
        Optional<User> actualFindByUsernameResult = this.userService.findByUsername("janedoe");
        assertSame(emptyResult, actualFindByUsernameResult);
        assertFalse(actualFindByUsernameResult.isPresent());
        verify(this.userRepository, atLeast(1)).findByUsername((String) any());
    }

    @Test
    void testFindUserByUsername() {
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
        when(this.userRepository.findByUsername((String) any())).thenReturn(ofResult);
        assertSame(user, this.userService.findUserByUsername("janedoe"));
        verify(this.userRepository).findByUsername((String) any());
    }

    @Test
    void testFindUserByUsername2() {
        when(this.userRepository.findByUsername((String) any())).thenReturn(Optional.<User>empty());
        assertThrows(UserNotFoundExeption.class, () -> this.userService.findUserByUsername("janedoe"));
        verify(this.userRepository).findByUsername((String) any());
    }

    @Test
    void testFindUserById() {
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
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(user, this.userService.findUserById(123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testFindUserById2() {
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.<User>empty());
        assertThrows(UserNotFoundExeption.class, () -> this.userService.findUserById(123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testFindById() {
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
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<User> actualFindByIdResult = this.userService.findById(123L);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(this.userRepository, atLeast(1)).findById((Long) any());
    }

    @Test
    void testFindById2() {
        Optional<User> emptyResult = Optional.<User>empty();
        when(this.userRepository.findById((Long) any())).thenReturn(emptyResult);
        Optional<User> actualFindByIdResult = this.userService.findById(123L);
        assertSame(emptyResult, actualFindByIdResult);
        assertFalse(actualFindByIdResult.isPresent());
        verify(this.userRepository, atLeast(1)).findById((Long) any());
    }

    @Test
    void testCreateNewUserAndFillBasicFields() {
        User actualCreateNewUserAndFillBasicFieldsResult = this.userService
                .createNewUserAndFillBasicFields(new UserDtoPayload("Jane", "Second Name", "Doe", "janedoe",
                        "jane.doe@example.org", "iloveyou", "4105551212", "https://example.org/example"));
        assertEquals("janedoe", actualCreateNewUserAndFillBasicFieldsResult.getUsername());
        assertEquals("Second Name", actualCreateNewUserAndFillBasicFieldsResult.getSecondName());
        assertEquals("4105551212", actualCreateNewUserAndFillBasicFieldsResult.getPhoneNumber());
        assertEquals("Doe", actualCreateNewUserAndFillBasicFieldsResult.getLastName());
        assertEquals("Jane", actualCreateNewUserAndFillBasicFieldsResult.getFirstName());
        assertEquals("jane.doe@example.org", actualCreateNewUserAndFillBasicFieldsResult.getEmail());
    }

    @Test
    void testCreateNewUserAndFillBasicFields2() {
        UserDtoPayload userDtoPayload = new UserDtoPayload("Jane", "Second Name", "Doe", "janedoe", "jane.doe@example.org",
                "iloveyou", "4105551212", "https://example.org/example");
        userDtoPayload.setLastName("Doe");
        User actualCreateNewUserAndFillBasicFieldsResult = this.userService.createNewUserAndFillBasicFields(userDtoPayload);
        assertEquals("janedoe", actualCreateNewUserAndFillBasicFieldsResult.getUsername());
        assertEquals("Second Name", actualCreateNewUserAndFillBasicFieldsResult.getSecondName());
        assertEquals("4105551212", actualCreateNewUserAndFillBasicFieldsResult.getPhoneNumber());
        assertEquals("Doe", actualCreateNewUserAndFillBasicFieldsResult.getLastName());
        assertEquals("Jane", actualCreateNewUserAndFillBasicFieldsResult.getFirstName());
        assertEquals("jane.doe@example.org", actualCreateNewUserAndFillBasicFieldsResult.getEmail());
    }

    @Test
    void testFindByEmail() {
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
        when(this.userRepository.findByEmail((String) any())).thenReturn(ofResult);
        Optional<User> actualFindByEmailResult = this.userService.findByEmail("jane.doe@example.org");
        assertSame(ofResult, actualFindByEmailResult);
        assertTrue(actualFindByEmailResult.isPresent());
        verify(this.userRepository, atLeast(1)).findByEmail((String) any());
    }

    @Test
    void testFindByEmail2() {
        Optional<User> emptyResult = Optional.<User>empty();
        when(this.userRepository.findByEmail((String) any())).thenReturn(emptyResult);
        Optional<User> actualFindByEmailResult = this.userService.findByEmail("jane.doe@example.org");
        assertSame(emptyResult, actualFindByEmailResult);
        assertFalse(actualFindByEmailResult.isPresent());
        verify(this.userRepository, atLeast(1)).findByEmail((String) any());
    }

    @Test
    void testConvertUserToUserDto() {
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
        UserDto actualConvertUserToUserDtoResult = this.userService.convertUserToUserDto(user);
        assertEquals("jane.doe@example.org", actualConvertUserToUserDtoResult.getEmail());
        assertEquals("janedoe", actualConvertUserToUserDtoResult.getUsername());
        assertEquals("Second Name", actualConvertUserToUserDtoResult.getSecondName());
        assertEquals("Name", actualConvertUserToUserDtoResult.getRole());
        assertEquals("4105551212", actualConvertUserToUserDtoResult.getPhoneNumber());
        assertEquals("Doe", actualConvertUserToUserDtoResult.getLastName());
        assertEquals(123L, actualConvertUserToUserDtoResult.getId());
        assertEquals("Jane", actualConvertUserToUserDtoResult.getFirstName());
    }

    @Test
    void testChangeInfo() throws IllegalAccessException, NoSuchFieldException {
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
        when(this.userRepository.save((User) any())).thenReturn(user);

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
        UserDto actualChangeInfoResult = this.userService.changeInfo(user1, new UserDtoPayload("Jane", "Second Name", "Doe",
                "janedoe", "jane.doe@example.org", "iloveyou", "4105551212", "https://example.org/example"));
        assertEquals("jane.doe@example.org", actualChangeInfoResult.getEmail());
        assertEquals("janedoe", actualChangeInfoResult.getUsername());
        assertEquals("Second Name", actualChangeInfoResult.getSecondName());
        assertEquals("Name", actualChangeInfoResult.getRole());
        assertEquals("4105551212", actualChangeInfoResult.getPhoneNumber());
        assertEquals("Doe", actualChangeInfoResult.getLastName());
        assertEquals(123L, actualChangeInfoResult.getId());
        assertEquals("Jane", actualChangeInfoResult.getFirstName());
        verify(this.userRepository).save((User) any());
    }

    @Test
    void testSendMessage() {
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
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

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

        Role role2 = new Role();
        role2.setId(123L);
        role2.setName("Name");

        User user2 = new User();
        user2.setLastName("Doe");
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setRole(role2);
        user2.setActivationCode("Activation Code");
        user2.setCreatedActivationCode(LocalDateTime.of(1, 1, 1, 1, 1));
        user2.setId(123L);
        user2.setFriends(new ArrayList<User>());
        user2.setPhoneNumber("4105551212");
        user2.setTimeOfAccountCreation(LocalDateTime.of(1, 1, 1, 1, 1));
        user2.setUserProfileImageUrl("https://example.org/example");
        user2.setFirstName("Jane");
        user2.setUsername("janedoe");
        user2.setSecondName("Second Name");

        Message message = new Message();
        message.setId(123L);
        message.setMsg_body("VBage Test Main Phrase");
        message.setSender(user1);
        message.setReciever(user2);
        when(this.messageRepository.save((Message) any())).thenReturn(message);
        this.userService.sendMessage(1L, 1L, "Msg");
        verify(this.userRepository, atLeast(1)).findById((Long) any());
        verify(this.messageRepository).save((Message) any());
    }

    @Test
    void testSendMessage2() {
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.<User>empty());

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
        message.setMsg_body("VBage Test Main Phrase");
        message.setSender(user);
        message.setReciever(user1);
        when(this.messageRepository.save((Message) any())).thenReturn(message);
        assertThrows(UserNotFoundExeption.class, () -> this.userService.sendMessage(1L, 1L, "Msg"));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testGetAllMsgs() {
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
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.messageRepository.findAllByReciever((User) any())).thenReturn(new ArrayList<Message>());
        assertTrue(this.userService.getAllMsgs(1L).isEmpty());
        verify(this.userRepository).findById((Long) any());
        verify(this.messageRepository).findAllByReciever((User) any());
    }

    @Test
    void testGetAllMsgs2() {
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.<User>empty());
        when(this.messageRepository.findAllByReciever((User) any())).thenReturn(new ArrayList<Message>());
        assertThrows(UserNotFoundExeption.class, () -> this.userService.getAllMsgs(1L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testGetAllMsgs3() {
        Role role = new Role();
        User user = new User();
        Optional<User> ofResult = Optional.<User>of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        Role role1 = new Role();
        User user1 = new User();
        user1.setId(123L);

        Role role2 = new Role();
        User user2 = new User();

        Message message = new Message();
        message.setId(123L);
        message.setMsg_body("VBage Test Main Phrase");
        message.setSender(user1);
        message.setReciever(user2);

        ArrayList<Message> messageList = new ArrayList<Message>();
        messageList.add(message);
        when(this.messageRepository.findAllByReciever((User) any())).thenReturn(messageList);
        List<MessageDto> actualAllMsgs = this.userService.getAllMsgs(1L);
        assertEquals(1, actualAllMsgs.size());
        MessageDto getResult = actualAllMsgs.get(0);
        assertEquals("VBage Test Main Phrase", getResult.getBody());
        assertEquals(123L, getResult.getSender_id().longValue());
        verify(this.userRepository).findById((Long) any());
        verify(this.messageRepository).findAllByReciever((User) any());
    }

    @Test
    void testGetAllFriends() {
        Role role = new Role();
        User user = new User();
        user.setFriends(new ArrayList<>());
        assertTrue(this.userService.getAllFriends(user).isEmpty());
    }

    @Test
    void testGetAllFriends2() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");

        User user = new User();
        user.setRole(role);

        ArrayList<User> userList = new ArrayList<User>();
        userList.add(user);

        User user1 = new User();
        user1.setFriends(userList);
        assertEquals(1, this.userService.getAllFriends(user1).size());
    }

    @Test
    void testAddFriend() {
        Role role = new Role();
        User user = new User();
        Optional<User> ofResult = Optional.<User>of(user);

        Role role1 = new Role();

        User user1 = new User();
        user.setFriends(new ArrayList<>());
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        this.userService.addFriend(1L, 1L);
        verify(this.userRepository, atLeast(1)).findById((Long) any());
        verify(this.userRepository).save((User) any());
    }

    @Test
    void testAddFriend2() {
        Role role = new Role();

        User user = new User();

        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.<User>empty());
        assertThrows(UserNotFoundExeption.class, () -> this.userService.addFriend(1L, 1L));
        verify(this.userRepository).findById((Long) any());
    }


    @Test
    void testDeleteFriend() {
        Role role = new Role();
        User user = new User();

        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.<User>empty());
        assertThrows(UserNotFoundExeption.class, () -> this.userService.deleteFriend(1L, 1L));
        verify(this.userRepository).findById((Long) any());
    }
}

