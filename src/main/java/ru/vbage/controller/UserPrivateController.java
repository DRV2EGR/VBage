package ru.vbage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.vbage.dto.UserDto;
import ru.vbage.entity.User;
import ru.vbage.exception.BadRequestException;
import ru.vbage.exception.UserNotFoundExeption;
import ru.vbage.payload.SendMessagePayload;
import ru.vbage.payload.UserDtoPayload;
import ru.vbage.service.UserService;

/**
 * The type User private controller.
 */
@RestController
@RequestMapping(value = "/v1/api/user/private", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserPrivateController {

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    private User getAuthentificatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            String currentUserName = authentication.getName();
            User currentUser = userService.findByUsername(currentUserName).orElseThrow(
                    () -> {throw new UserNotFoundExeption("");}
            );

            return currentUser;
        } catch (Exception e) {
            throw new BadRequestException("No user in auth");
        }

    }

    /**
     * Gets all user info.
     *
     * @param id the id
     * @return the all user info
     */
    @GetMapping("/user")
    public ResponseEntity<UserDto> getAllUserInfo(@RequestParam long id) {
        User user = getAuthentificatedUser();

        return ResponseEntity.ok(userService.convertUserToUserDto(user));
    }

    /**
     * Change user info response entity.
     *
     * @param userDtoPayload the user dto payload
     * @return the response entity
     * @throws NoSuchFieldException   the no such field exception
     * @throws IllegalAccessException the illegal access exception
     */
    @PutMapping("/user")
    public ResponseEntity<UserDto> changeUserInfo(@RequestBody UserDtoPayload userDtoPayload) throws NoSuchFieldException, IllegalAccessException {
        return ResponseEntity.ok(userService.changeInfo(getAuthentificatedUser(), userDtoPayload));
    }

    @PostMapping("/send_message")
    public ResponseEntity<?> sendMessage(@RequestBody SendMessagePayload sendMessagePayload) {
        userService.sendMessage(getAuthentificatedUser().getId(), sendMessagePayload.getId_to(), sendMessagePayload.getBody());

        return ResponseEntity.ok("OK");
    }

    @GetMapping("/get_all_messages")
    public ResponseEntity<?> getAllRecievedMessages() {
        return ResponseEntity.ok(userService.getAllMsgs(getAuthentificatedUser().getId()));
    }

    @GetMapping("/add_friend")
    public ResponseEntity<?> addFriend(@RequestParam Long friend_id) {
        userService.addFriend(getAuthentificatedUser().getId(), friend_id);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/delete_friend")
    public ResponseEntity<?> deleteFriend(@RequestParam Long friend_id) {
        userService.deleteFriend(getAuthentificatedUser().getId(), friend_id);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/all_friends")
    public ResponseEntity<?> getAllFriends() {
        return ResponseEntity.ok(userService.getAllFriends(getAuthentificatedUser()));
    }
}
