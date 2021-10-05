package ru.vbage.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vbage.dto.MessageDto;
import ru.vbage.dto.UserDto;
import ru.vbage.entity.Message;
import ru.vbage.entity.User;
import ru.vbage.exception.ConflictException;
import ru.vbage.exception.UserNotFoundExeption;
import ru.vbage.payload.BasicPayload;
import ru.vbage.payload.UserDtoPayload;
import ru.vbage.repository.MessageRepository;
import ru.vbage.repository.RoleRepository;
import ru.vbage.repository.UserRepository;

/**
 * The type User service.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MessageRepository messageRepository;

    /**
     * The B crypt password encoder.
     */
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * The Log.
     */
    Logger log = LoggerFactory.getLogger("securityLogger");

    /**
     * Password encoder b crypt password encoder.
     *
     * @return the b crypt password encoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    public Optional<User> findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent())
        log.info("User " + optionalUser.get().toString() + "found by username " + username);
        else log.info("User with username '" + username + "' not found.");
        return userRepository.findByUsername(username);
    }

    /**
     * Find user by username user.
     *
     * @param username the username
     * @return the user
     */
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundExeption("User with this username not found!")
        );
    }

    /**
     * Find user by id user.
     *
     * @param userId the user id
     * @return the user
     */
    public User findUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundExeption("User with this id not found!")
        );
    }

    /**
     * Find by id optional.
     *
     * @param userId the user id
     * @return the optional
     */
    public Optional<User> findById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent())
            log.info("User " + optionalUser.get().toString() + "found by id " + userId);
        else log.info("User with id '" + userId + "' not found.");
        return userRepository.findById(userId);
    }

    /**
     * Create new user and fill basic fields user.
     *
     * @param basicPayload the basic payload
     * @return the user
     */
    public User createNewUserAndFillBasicFields(BasicPayload basicPayload) {
        User user = new User();

        user.setUsername(basicPayload.getUsername());
        user.setEmail(basicPayload.getEmail());
        user.setFirstName(basicPayload.getFirstName());
        user.setLastName(basicPayload.getLastName());
        user.setPhoneNumber(basicPayload.getPhoneNumber());
        user.setSecondName(basicPayload.getSecondName());

        return user;
    }


    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    public Optional<User> findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent())
            log.info("User " + optionalUser.get().toString() + "found by email " + email);
        else log.info("User with email '" + email + "' not found.");
        return userRepository.findByEmail(email);
    }

    /**
     * Convert user to user dto user dto.
     *
     * @param user the user
     * @return the user dto
     */
    public UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole().getName());
        userDto.setSecondName(user.getSecondName());
        userDto.setPhoneNumber(user.getPhoneNumber());

        return userDto;
    }

    /**
     * Change info user dto.
     *
     * @param user           the user
     * @param userDtoPayload the user dto payload
     * @return the user dto
     * @throws NoSuchFieldException   the no such field exception
     * @throws IllegalAccessException the illegal access exception
     */
    public UserDto changeInfo(User user, UserDtoPayload userDtoPayload) throws NoSuchFieldException, IllegalAccessException {
        System.out.println(userDtoPayload.getClass().getDeclaredFields().length);
        for (Field obj : userDtoPayload.getClass().getDeclaredFields()) {
            if (obj.getName().equals("username") || obj.getName().equals("email")) {
                continue;
            }

            Field field = user.getClass().getDeclaredField(obj.getName());
            field.setAccessible(true);
            Field field1 = userDtoPayload.getClass().getDeclaredField(obj.getName());
            field1.setAccessible(true);

            if (field1.get(userDtoPayload) != null) {
                field.set(user, (String) field1.get(userDtoPayload));
            }
        }

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new ConflictException("Incorrect value");
        }

        return convertUserToUserDto(user);
    }

    public void sendMessage(Long id_from, Long id_to, String msg) {
        Message message = new Message();
        message.setSender(userRepository.findById(id_from).orElseThrow(
                () -> new UserNotFoundExeption("No such sender!")
        ));
        message.setReciever(userRepository.findById(id_to).orElseThrow(
                () -> new UserNotFoundExeption("No such sender!")
        ));
        message.setMsg_body(msg);

        messageRepository.save(message);
    }

    public List<MessageDto> getAllMsgs(Long user_id) {
        List<MessageDto> res = new ArrayList<>();
        for(Message message: messageRepository.findAllByReciever(userRepository.findById(user_id).orElseThrow(
                () -> new UserNotFoundExeption("No such user!")
        ))) {
            res.add(new MessageDto(message.getSender().getId(), message.getMsg_body()));
        }
        return res;
    }

    public List<UserDto> getAllFriends(User user) {
        List<UserDto> rst = new ArrayList<>();
        for(User user1 : user.getFriends()) {
            rst.add(this.convertUserToUserDto(user1));
        }

        return rst;
    }

    public void addFriend(Long user_id, Long friend_id) {
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new UserNotFoundExeption("No such user!")
        );
        List<User> rst = user.getFriends();

        if (rst.contains(userRepository.findById(friend_id).orElseThrow(
                () -> new UserNotFoundExeption("No such user!")
        ))) {
            throw new ConflictException("Already friends!");
        }

        rst.add(userRepository.findById(friend_id).orElseThrow(
                () -> new UserNotFoundExeption("No such user!")
        ));
        user.setFriends(rst);

        userRepository.save(user);
    }

    public void deleteFriend(Long user_id, Long friend_id) {
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new UserNotFoundExeption("No such user!")
        );
        List<User> rst = user.getFriends();
        rst.remove(userRepository.findById(friend_id).orElseThrow(
                () -> new UserNotFoundExeption("No such user!")
        ));
        user.setFriends(rst);

        userRepository.save(user);
    }

    public UserDto registerNewUser(UserDtoPayload userDtoPayload) {
        User user = createNewUserAndFillBasicFields(userDtoPayload);
        user.setRole(roleRepository.findById(1L).get());

        user.setFriends(new ArrayList<>());
        userRepository.save(user);
        return convertUserToUserDto(user);
    }

}
